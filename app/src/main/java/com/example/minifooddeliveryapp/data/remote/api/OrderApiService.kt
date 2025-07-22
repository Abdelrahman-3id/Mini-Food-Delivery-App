package com.example.minifooddeliveryapp.data.remote.api

import com.example.minifooddeliveryapp.data.dto.OrderDto
import retrofit2.http.GET
import retrofit2.http.Path

interface OrderApiService {
    @GET("orders")
    suspend fun getOrders(): List<OrderDto>

    @GET("orders/{id}")
    suspend fun getOrderDetails(@Path("id") orderId: String): OrderDto

    companion object {
        const val BASE_URL = "http://10.0.2.2:8080/"
    }
}