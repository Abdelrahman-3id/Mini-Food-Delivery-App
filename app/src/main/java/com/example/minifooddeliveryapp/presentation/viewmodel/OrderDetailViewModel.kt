package com.example.minifooddeliveryapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minifooddeliveryapp.domain.model.Order
import com.example.minifooddeliveryapp.domain.usecase.GetOrderDetailsUseCase
import com.example.minifooddeliveryapp.domain.usecase.SubscribeToOrderUpdatesUseCase
import com.example.minifooddeliveryapp.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    private val getOrderDetailsUseCase: GetOrderDetailsUseCase,
    private val subscribeToOrderUpdatesUseCase: SubscribeToOrderUpdatesUseCase
) : ViewModel() {
    var state = mutableStateOf<UiState<Order>>(UiState.Loading)
        private set

    private var updatesJob: Job? = null

    fun loadOrderDetails(orderId: String) {
        viewModelScope.launch {
            state.value = UiState.Loading
            try {
                val order = getOrderDetailsUseCase(orderId)
                state.value = UiState.Success(order)
                startObservingUpdates(orderId)
            } catch (e: Exception) {
                state.value = UiState.Error(e.message ?: "Failed to load order details")
            }
        }
    }

    private fun startObservingUpdates(orderId: String) {
        updatesJob?.cancel()
        updatesJob = viewModelScope.launch {
            subscribeToOrderUpdatesUseCase(orderId).collect { update ->
                state.value = state.value.let { currentState ->
                    when (currentState) {
                        is UiState.Success -> UiState.Success(
                            currentState.data.copy(status = update.newStatus)
                        )

                        else -> currentState
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        updatesJob?.cancel()
    }
}