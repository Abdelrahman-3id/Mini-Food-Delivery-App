package com.example.minifooddeliveryapp.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minifooddeliveryapp.domain.model.Order
import com.example.minifooddeliveryapp.domain.usecase.GetOrdersUseCase
import com.example.minifooddeliveryapp.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor(
    private val getOrdersUseCase: GetOrdersUseCase
) : ViewModel() {
    var state = mutableStateOf<UiState<List<Order>>>(UiState.Loading)
        private set

    init {
        loadOrders()
    }

    fun loadOrders() {
        viewModelScope.launch {
            state.value = UiState.Loading
            try {
                val orders = getOrdersUseCase()
                state.value = UiState.Success(orders)
            } catch (e: Exception) {
                state.value = UiState.Error(e.message ?: "Failed to load orders")
            }
        }
    }
}