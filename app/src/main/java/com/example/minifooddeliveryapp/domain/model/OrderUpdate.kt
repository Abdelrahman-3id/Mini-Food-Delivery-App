package com.example.minifooddeliveryapp.domain.model

data class OrderUpdate(
    val orderId: String,
    val newStatus: OrderStatus,
    val timestamp: Long = System.currentTimeMillis()
)