package com.example.minifooddeliveryapp.data.repository

import com.example.minifooddeliveryapp.data.local.OrderEntity
import com.example.minifooddeliveryapp.data.local.dao.OrderDao
import com.example.minifooddeliveryapp.data.remote.api.OrderApiService
import com.example.minifooddeliveryapp.data.remote.websocket.WebSocketService
import com.example.minifooddeliveryapp.domain.Repository.OrderRepository
import com.example.minifooddeliveryapp.domain.model.Order
import com.example.minifooddeliveryapp.domain.model.OrderStatus
import com.example.minifooddeliveryapp.domain.model.OrderUpdate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class OrderRepositoryImpl @Inject constructor(
    private val apiService: OrderApiService,
    private val webSocketService: WebSocketService,
    private val orderDao: OrderDao
) : OrderRepository {
    private val orderUpdatesMap = mutableMapOf<String, MutableSharedFlow<OrderUpdate>>()

    override suspend fun getOrders(): List<Order> {
        return apiService.getOrders().map { it.toOrder() }
    }

    override suspend fun getOrderDetails(orderId: String): Order {
        return apiService.getOrderDetails(orderId).toOrder()
    }

    override fun subscribeToOrderUpdates(orderId: String): Flow<OrderUpdate> {
        return orderUpdatesMap.getOrPut(orderId) {
            MutableSharedFlow()
        }.asSharedFlow()
    }

    override suspend fun cacheOrders(orders: List<Order>) {
        orderDao.clearOrders()
        orderDao.insertOrders(orders.map { OrderEntity.fromOrder(it) })
    }

    override suspend fun getCachedOrders(): List<Order> {
        return orderDao.getOrders().map { it.toOrder() }
    }

    init {
        observeWebSocketUpdates()
    }

    private fun observeWebSocketUpdates() {
        CoroutineScope(Dispatchers.IO).launch {
            webSocketService.updates.collect { update ->
                orderUpdatesMap[update.orderId]?.emit(
                    OrderUpdate(
                        orderId = update.orderId,
                        newStatus = OrderStatus.fromString(update.newStatus)
                    )
                )
            }
        }
    }

}