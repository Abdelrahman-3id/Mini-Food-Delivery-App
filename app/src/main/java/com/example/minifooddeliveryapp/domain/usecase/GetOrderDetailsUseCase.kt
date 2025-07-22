package com.example.minifooddeliveryapp.domain.usecase

import com.example.minifooddeliveryapp.domain.Repository.OrderRepository
import com.example.minifooddeliveryapp.domain.model.Order
import javax.inject.Inject

class GetOrderDetailsUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(orderId: String): Order {
        return repository.getOrderDetails(orderId)
    }
}