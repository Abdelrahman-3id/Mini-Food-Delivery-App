package com.example.minifooddeliveryapp.domain.model

data class Order(
    val id: String,
    val customerName: String,
    val restaurant: String,
    val status: OrderStatus,
    val items: List<OrderItem> = emptyList(),
    val totalPrice: Double = 0.0,
    val deliveryAddress: String = "",
    val estimatedDeliveryTime: String = ""
) {
    companion object {
        val mockOrders = listOf(
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
                deliveryAddress = "123 Main St",
                estimatedDeliveryTime = "30-45 mins"
            )
        )
    }
}

data class OrderItem(
    val name: String,
    val quantity: Int,
    val price: Double
)

enum class OrderStatus {
    PLACED, PREPARING, OUT_FOR_DELIVERY, DELIVERED, CANCELLED;

    companion object {
        fun fromString(status: String): OrderStatus {
            return when (status.lowercase()) {
                "placed" -> PLACED
                "preparing" -> PREPARING
                "out for delivery" -> OUT_FOR_DELIVERY
                "delivered" -> DELIVERED
                "cancelled" -> CANCELLED
                else -> PLACED
            }
        }
    }
}