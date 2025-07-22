package com.example.minifooddeliveryapp.data.local.db.AppDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.minifooddeliveryapp.data.local.OrderEntity
import com.example.minifooddeliveryapp.data.local.dao.OrderDao

@Database(
    entities = [OrderEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao

    companion object {
        const val DATABASE_NAME = "food_delivery_db"
    }
}