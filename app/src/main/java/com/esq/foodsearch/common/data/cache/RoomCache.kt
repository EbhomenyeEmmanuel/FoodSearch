package com.esq.foodsearch.common.data.cache

import com.esq.foodsearch.common.data.cache.daos.FoodDao
import com.esq.foodsearch.common.data.cache.model.cachedfood.CachedFood
import io.reactivex.Flowable
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val foodDao: FoodDao
) : Cache {

    override suspend fun storeFoodsToDb(cachedFoods: List<CachedFood>) {
        foodDao.insertFoodIntoCache(cachedFoods)
    }

    override fun getListOfFoodsFromDb(): Flowable<List<CachedFood>> {
        return foodDao.getAllAnimals()
    }

}