package com.example.minifooddeliveryapp.di

import com.example.minifooddeliveryapp.data.local.dao.OrderDao
import com.example.minifooddeliveryapp.data.remote.api.OrderApiService
import com.example.minifooddeliveryapp.data.remote.websocket.WebSocketService
import com.example.minifooddeliveryapp.data.repository.OrderRepositoryImpl
import com.example.minifooddeliveryapp.domain.Repository.OrderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideOrderRepository(
        apiService: OrderApiService,
        webSocketService: WebSocketService,
        orderDao: OrderDao
    ): OrderRepository{
        return OrderRepositoryImpl(apiService,webSocketService,orderDao)
    }

}