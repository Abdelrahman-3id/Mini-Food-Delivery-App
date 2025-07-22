package com.example.minifooddeliveryapp.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.minifooddeliveryapp.domain.model.Order
import com.example.minifooddeliveryapp.presentation.model.UiState
import com.example.minifooddeliveryapp.presentation.ui.components.CenterLoading
import com.example.minifooddeliveryapp.presentation.ui.components.ErrorView
import com.example.minifooddeliveryapp.presentation.ui.components.OrderStatusChip
import com.example.minifooddeliveryapp.presentation.viewmodel.OrderListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderListScreen(
    viewModel: OrderListViewModel = hiltViewModel(),
    onOrderClick: (String) -> Unit
) {
    val state by viewModel.state
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Orders") },
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
                    onRetry = { viewModel.loadOrders() }
                )
                is UiState.Success<*> -> OrderListContent(
                    orders = currentState.data as List<Order>,
                    onOrderClick = onOrderClick
                )

            }
        }
    }
}

@Composable
private fun OrderListContent(
    orders: List<Order>,
    onOrderClick: (String) -> Unit
) {
    LazyColumn {
        items(orders) { order ->
            OrderListItem(
                order = order,
                onClick = { onOrderClick(order.id) }
            )
        }
    }
}

@Composable
private fun OrderListItem(
    order: Order,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = order.restaurant,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Order for ${order.customerName}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            OrderStatusChip(status = order.status)
        }
    }
}