package com.example.minifooddeliveryapp.data.dto

import com.example.minifooddeliveryapp.domain.model.Order
import com.example.minifooddeliveryapp.domain.model.OrderItem
import com.example.minifooddeliveryapp.domain.model.OrderStatus
import com.google.gson.annotations.SerializedName

data class OrderDto(
    @SerializedName("id") val id: String,
    @SerializedName("customerName") val customerName: String,
    @SerializedName("restaurant") val restaurant: String,
    @SerializedName("status") val status: String,
    @SerializedName("items") val items: List<OrderItemDto>?,
    @SerializedName("totalPrice") val totalPrice: Double?,
    @SerializedName("deliveryAddress") val deliveryAddress: String?,
    @SerializedName("estimatedDeliveryTime") val estimatedDeliveryTime: String?
) {
    fun toOrder(): Order {
        return Order(
            id = id,
            customerName = customerName,
            restaurant = restaurant,
            status = OrderStatus.fromString(status),
            items = items?.map { it.toOrderItem() } ?: emptyList(),
            totalPrice = totalPrice ?: 0.0,
            deliveryAddress = deliveryAddress ?: "",
            estimatedDeliveryTime = estimatedDeliveryTime ?: ""
        )
    }
}

data class OrderItemDto(
    @SerializedName("name") val name: String,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("price") val price: Double
) {
    fun toOrderItem(): OrderItem {
        return OrderItem(
            name = name,
            quantity = quantity,
            price = price
        )
    }
}