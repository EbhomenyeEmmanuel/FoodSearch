package com.esq.foodsearch.common.data.cache

import com.esq.foodsearch.common.data.cache.model.cachedfood.CachedFood
import io.reactivex.Flowable

interface Cache {
    suspend fun storeFoodsToDb(cachedFoods: List<CachedFood>)

    fun getListOfFoodsFromDb(): Flowable<List<CachedFood>>
}