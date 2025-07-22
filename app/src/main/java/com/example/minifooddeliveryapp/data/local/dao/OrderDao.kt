package com.example.minifooddeliveryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.minifooddeliveryapp.data.local.OrderEntity

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrders(orders: List<OrderEntity>)

    @Query("SELECT * FROM orders")
    suspend fun getOrders(): List<OrderEntity>

    @Query("DELETE FROM orders")
    suspend fun clearOrders()
}