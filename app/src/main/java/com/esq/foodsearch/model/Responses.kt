package com.esq.foodsearch.model

import com.google.gson.annotations.JsonAdapter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.ArrayList

/*
 *POJO class to get token
 */
@JsonClass(generateAdapter = true)
data
class UserTokenModel (
    @Json(name = "access_token")
    val token: String? = null,
    @Json(name = "expires_in")
    val tokenExpirationSeconds : Int = 0)


data class LoginModel(val user: String, val password: String)

@JsonClass(generateAdapter = true)
data class Foods (
        @Json(name = "food")
        var food: ArrayList<Food>? = null
        ,
        @Json(name = "max_results")
        var maxResults: String? = null
        ,
        @Json(name = "page_number")
        var pageNumber: String? = null
        ,
        @Json(name = "total_results")
        var totalResults: String? = null)

@JsonClass(generateAdapter = true)
data class FoodModelClass (
        @Json(name = "foods")
        var foods: Foods? = null)

/*
 *Food class contain details about a specified food
 */
@JsonClass(generateAdapter = true)
data class Food(
        @Json(name = "food_description")
        var foodDescription: String? = null
        ,
        @Json(name = "food_id")
        var foodId: String? = null
        ,
        @Json(name = "food_name")
        var foodName: String? = null
        ,
        @Json(name = "food_type")
        var foodType: String? = null
        ,
        @Json(name = "food_url")
        var foodUrl: String? = null
        ,
        @Json(name = "brand_name")
        var brandName: String? = null
)