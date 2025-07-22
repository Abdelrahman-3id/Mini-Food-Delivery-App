package com.example.minifooddeliveryapp.utils

import com.example.minifooddeliveryapp.domain.model.Order
import com.example.minifooddeliveryapp.domain.model.OrderItem
import com.example.minifooddeliveryapp.domain.model.OrderStatus

object MockDataGenerator {
    fun generateMockOrders(): List<Order> {
        return listOf(
            Order(
                id = "order1",
                customerName = "John Doe",
                restaurant = "Burger King",
                status = OrderStatus.PREPARING,
                items = listOf(
                    OrderItem("Whopper", 1, 5.99),
                    OrderItem("Fries", 2, 2.49)
                ),
                totalPrice = 10.97,
                deliveryAddress = "123 Main St, New York",
                estimatedDeliveryTime = "30-45 mins"
            ),
            Order(
                id = "order2",
                customerName = "Jane Smith",
                restaurant = "Pizza Hut",
                status = OrderStatus.OUT_FOR_DELIVERY,
                items = listOf(
                    OrderItem("Pepperoni Pizza", 1, 12.99),
                    OrderItem("Garlic Bread", 1, 4.99),
                    OrderItem("Soda", 2, 1.99)
                ),
                totalPrice = 21.96,
                deliveryAddress = "456 Oak Ave, Brooklyn",
                estimatedDeliveryTime = "15-30 mins"
            )
        )
    }
}