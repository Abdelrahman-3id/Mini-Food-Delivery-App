package com.example.minifooddeliveryapp.domain.usecase

import com.example.minifooddeliveryapp.domain.Repository.OrderRepository
import com.example.minifooddeliveryapp.domain.model.Order
import javax.inject.Inject


class GetOrdersUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(): List<Order> {
        return try {
            val orders = repository.getOrders()
            repository.cacheOrders(orders)
            orders
        } catch (e: Exception) {
            repository.getCachedOrders()
        }
    }
}