package com.example.minifooddeliveryapp.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.minifooddeliveryapp.domain.model.Order
import com.example.minifooddeliveryapp.presentation.model.UiState
import com.example.minifooddeliveryapp.presentation.ui.components.CenterLoading
import com.example.minifooddeliveryapp.presentation.ui.components.DeliveryInfoSection
import com.example.minifooddeliveryapp.presentation.ui.components.ErrorView
import com.example.minifooddeliveryapp.presentation.ui.components.OrderHeaderSection
import com.example.minifooddeliveryapp.presentation.ui.components.OrderItemsSection
import com.example.minifooddeliveryapp.presentation.ui.components.OrderStatusSection
import com.example.minifooddeliveryapp.presentation.ui.components.OrderSummarySection
import com.example.minifooddeliveryapp.presentation.viewmodel.OrderDetailViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailScreen(
    orderId: String,
    viewModel: OrderDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    LaunchedEffect(orderId) {
        viewModel.loadOrderDetails(orderId)
    }

    val state by viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Order Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (val currentState = state) {
                is UiState.Loading -> CenterLoading()
                is UiState.Error -> ErrorView(
                    message = currentState.message,
                    onRetry = { viewModel.loadOrderDetails(orderId) }
                )
                is UiState.Success<*> -> OrderDetailContent(order = currentState.data as Order)
            }
        }
    }
}

@Composable
private fun OrderDetailContent(order: Order) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OrderHeaderSection(order)
        OrderStatusSection(order)
        OrderItemsSection(order)
        OrderSummarySection(order)
        DeliveryInfoSection(order)
    }
}