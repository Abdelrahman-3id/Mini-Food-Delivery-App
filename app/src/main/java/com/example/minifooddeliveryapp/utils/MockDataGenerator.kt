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
    fun generateMockOrderDetail(orderId: String): Order {
        return when (orderId) {
            "order1" -> createBurgerOrder()
            "order2" -> createPizzaOrder()
            "order3" -> createSushiOrder()
            else -> createDefaultOrder()
        }
    }

    private fun createBurgerOrder(): Order {
        return Order(
            id = "order1",
            customerName = "John BurgerLover",
            restaurant = "Burger Palace",
            status = OrderStatus.OUT_FOR_DELIVERY,
            items = listOf(
                OrderItem("Double Cheeseburger", 2, 8.99),
                OrderItem("Bacon Fries", 1, 4.49),
                OrderItem("Chocolate Shake", 1, 3.99)
            ),
            totalPrice = 26.46,
            deliveryAddress = "123 Main St, Foodville, 10001",
            estimatedDeliveryTime = "15-25 mins",
            lastUpdated = System.currentTimeMillis() - 1800000 // 30 mins ago
        )
    }

    private fun createPizzaOrder(): Order {
        return Order(
            id = "order2",
            customerName = "Maria PizzaFan",
            restaurant = "Pizza Heaven",
            status = OrderStatus.PREPARING,
            items = listOf(
                OrderItem("Margherita Pizza (Large)", 1, 14.99),
                OrderItem("Garlic Breadsticks", 1, 5.49),
                OrderItem("Caesar Salad", 1, 7.99),
                OrderItem("Soda (2L)", 1, 3.49)
            ),
            totalPrice = 31.96,
            deliveryAddress = "456 Oak Ave, Flavortown, 10002",
            estimatedDeliveryTime = "35-45 mins",
            lastUpdated = System.currentTimeMillis() - 900000 // 15 mins ago
        )
    }

    private fun createSushiOrder(): Order {
        return Order(
            id = "order3",
            customerName = "Alex SushiMaster",
            restaurant = "Sushi World",
            status = OrderStatus.DELIVERED,
            items = listOf(
                OrderItem("Dragon Roll", 1, 12.99),
                OrderItem("Salmon Nigiri (5pc)", 2, 9.99),
                OrderItem("Miso Soup", 1, 2.99),
                OrderItem("Green Tea", 1, 1.99)
            ),
            totalPrice = 37.95,
            deliveryAddress = "789 Fish St, Seafood City, 10003",
            estimatedDeliveryTime = "Delivered 15 mins ago",
            lastUpdated = System.currentTimeMillis() - 900000 // 15 mins ago
        )
    }

    private fun createDefaultOrder(): Order {
        return Order(
            id = "order0",
            customerName = "Test Customer",
            restaurant = "Test Restaurant",
            status = OrderStatus.PLACED,
            items = listOf(
                OrderItem("Test Item 1", 1, 10.00),
                OrderItem("Test Item 2", 2, 5.00)
            ),
            totalPrice = 20.00,
            deliveryAddress = "000 Test Address",
            estimatedDeliveryTime = "45-60 mins",
            lastUpdated = System.currentTimeMillis()
        )
    }
}