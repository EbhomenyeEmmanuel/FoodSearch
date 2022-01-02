package com.esq.foodsearch.common.domain.model.pagination

import com.esq.foodsearch.common.domain.model.food.Food

data class PaginatedFoods(
    val foods: List<Food>,
    val pagination: Pagination
)