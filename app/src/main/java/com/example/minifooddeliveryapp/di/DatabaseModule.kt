package com.example.minifooddeliveryapp.di

import android.content.Context
import androidx.room.Room
import com.example.minifooddeliveryapp.data.local.dao.OrderDao
import com.example.minifooddeliveryapp.data.local.db.AppDatabase.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, "food_delivery_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideDatabaseDAO(database: AppDatabase): OrderDao = database.orderDao()
}