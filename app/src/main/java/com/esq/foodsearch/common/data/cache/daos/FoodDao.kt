package com.esq.foodsearch.common.data.cache.daos

import androidx.room.*
import com.esq.foodsearch.common.data.cache.model.cachedfood.CachedFood
import io.reactivex.Flowable

@Dao
interface FoodDao {
    @Query("SELECT * FROM cachedfood")
    fun getAllAnimals(): Flowable<List<CachedFood>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(
        cachedFood: CachedFood
    )

    suspend fun insertFoodIntoCache(
        cachedFood: List<CachedFood>
    ) {
        for (food in cachedFood) {
            insertFood(food)
        }
    }
}