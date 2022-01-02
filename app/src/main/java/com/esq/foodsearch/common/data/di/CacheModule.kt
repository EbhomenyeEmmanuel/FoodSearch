package com.esq.foodsearch.common.data.di

import android.content.Context
import androidx.room.Room
import com.esq.foodsearch.common.data.cache.Cache
import com.esq.foodsearch.common.data.cache.FoodSearchDatabase
import com.esq.foodsearch.common.data.cache.RoomCache
import com.esq.foodsearch.common.data.cache.daos.FoodDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindCache(cache: RoomCache): Cache

    companion object {

        @Provides
        fun provideFoodDao(foodSearchDatabase: FoodSearchDatabase): FoodDao =
            foodSearchDatabase.foodDao()

        @Provides
        @Singleton // 1
        fun provideDatabase(
            @ApplicationContext context: Context // 2
        ): FoodSearchDatabase {
            return Room.databaseBuilder( // 3
                context,
                FoodSearchDatabase::class.java,
                "foodsearch.db"
            ).build()
        }
    }
}