package com.esq.foodsearch.common.domain.model.food

/*
 * Domain representation of Food
 */
data class Food(
    val id: String? = null,
    val foodName: String? = null,
    val foodType: String? = null,
    val brandName: String? = null,
    val foodUrl: String? = null,
    val foodDescription: String? = null
)