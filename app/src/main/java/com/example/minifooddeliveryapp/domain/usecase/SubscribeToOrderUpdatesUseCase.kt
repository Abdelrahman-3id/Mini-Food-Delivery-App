package com.example.minifooddeliveryapp.domain.usecase

import com.example.minifooddeliveryapp.domain.Repository.OrderRepository
import com.example.minifooddeliveryapp.domain.model.OrderUpdate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubscribeToOrderUpdatesUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(orderId: String): Flow<OrderUpdate> {
        return repository.subscribeToOrderUpdates(orderId)
    }
}