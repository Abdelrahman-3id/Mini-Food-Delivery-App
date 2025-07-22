package com.example.minifooddeliveryapp.domain.Repository

import com.example.minifooddeliveryapp.domain.model.Order
import com.example.minifooddeliveryapp.domain.model.OrderUpdate
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    suspend fun getOrders(): List<Order>
    suspend fun getOrderDetails(orderId: String): Order
    fun subscribeToOrderUpdates(orderId: String): Flow<OrderUpdate>
    suspend fun cacheOrders(orders: List<Order>)
    suspend fun getCachedOrders(): List<Order>
}