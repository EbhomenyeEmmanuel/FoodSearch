package com.esq.foodsearch.common.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.esq.foodsearch.common.data.cache.daos.FoodDao
import com.esq.foodsearch.common.data.cache.model.cachedfood.CachedFood

@Database(entities = [CachedFood::class], version = 1)
abstract class FoodSearchDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}