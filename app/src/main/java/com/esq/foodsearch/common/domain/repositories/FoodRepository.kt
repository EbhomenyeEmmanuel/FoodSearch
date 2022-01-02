package com.esq.foodsearch.common.domain.repositories

import com.esq.foodsearch.common.domain.model.food.Food
import com.esq.foodsearch.common.domain.model.pagination.PaginatedFoods
import com.esq.foodsearch.common.domain.model.search.SearchParameter
import io.reactivex.Flowable

interface FoodRepository {
    fun getFoods(): Flowable<List<Food>>

    suspend fun requestFoodsUsingSearchKey(searchParameter: SearchParameter): PaginatedFoods

    suspend fun storeFoods(animals: List<Food>)

}