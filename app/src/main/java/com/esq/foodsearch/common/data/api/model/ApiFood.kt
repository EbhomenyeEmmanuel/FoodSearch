package com.esq.foodsearch.common.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiFoodModelClass(
    @field:Json(name = "foods")
    var apiPaginatedFoods: ApiPaginatedFoods? = null
)

@JsonClass(generateAdapter = true)
data class ApiPaginatedFoods(
    @field:Json(name = "food")
    var apiFoods: ArrayList<ApiFood>,
    @field:Json(name = "max_results")
    var maxResults: String,
    @field:Json(name = "page_number")
    var pageNumber: String,
    @field:Json(name = "total_results")
    var totalResults: String
)

@JsonClass(generateAdapter = true)
data class ApiFood(
    @field:Json(name = "food_id")
    var foodId: String? = null,
    @field:Json(name = "food_name")
    var foodName: String? = null,
    @field:Json(name = "food_type")
    var foodType: String? = null,
    @field:Json(name = "brand_name")
    var brandName: String? = null,
    @field:Json(name = "food_url")
    var foodUrl: String? = null,
    @field:Json(name = "food_description")
    var foodDescription: String? = null
)
