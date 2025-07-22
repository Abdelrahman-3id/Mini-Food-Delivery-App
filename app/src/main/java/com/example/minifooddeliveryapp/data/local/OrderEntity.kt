package com.example.minifooddeliveryapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.minifooddeliveryapp.domain.model.Order
import com.example.minifooddeliveryapp.domain.model.OrderItem
import com.example.minifooddeliveryapp.domain.model.OrderStatus
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey val id: String,
    val customerName: String,
    val restaurant: String,
    val status: String,
    val itemsJson: String,
    val totalPrice: Double,
    val deliveryAddress: String,
    val estimatedDeliveryTime: String
) {
    fun toOrder(): Order {
        return Order(
            id = id,
            customerName = customerName,
            restaurant = restaurant,
            status = OrderStatus.fromString(status),
            items = parseItemsJson(itemsJson),
            totalPrice = totalPrice,
            deliveryAddress = deliveryAddress,
            estimatedDeliveryTime = estimatedDeliveryTime
        )
    }

    companion object {
        fun fromOrder(order: Order): OrderEntity {
            return OrderEntity(
                id = order.id,
                customerName = order.customerName,
                restaurant = order.restaurant,
                status = order.status.name,
                itemsJson = Gson().toJson(order.items),
                totalPrice = order.totalPrice,
                deliveryAddress = order.deliveryAddress,
                estimatedDeliveryTime = order.estimatedDeliveryTime
            )
        }

        private fun parseItemsJson(json: String): List<OrderItem> {
            return try {
                Gson().fromJson(json, object : TypeToken<List<OrderItem>>() {}.type)
            } catch (e: Exception) {
                emptyList()
            }
        }
    }
}