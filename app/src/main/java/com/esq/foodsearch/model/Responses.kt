package com.esq.foodsearch.model

import com.google.gson.annotations.SerializedName
/*
 *POJO class to get token
 */

data class UserTokenModel(
        @SerializedName("access_token")
        val token: String? = null,
        @SerializedName("expires_in")
        val tokenExpirationSeconds: Int = 0)

data class LoginModel(val user: String, val password: String)

data class FoodModelClass(
        @SerializedName("foods")
        var foods: Foods? = null)

data class Foods(
        @SerializedName("food")
        var food: ArrayList<Food>
        ,
        @SerializedName("max_results")
        var maxResults: String
        ,
        @SerializedName("page_number")
        var pageNumber: String
        ,
        @SerializedName("total_results")
        var totalResults: String)

data class Food(
        @SerializedName("food_id")
        var foodId: String? = null
        ,
        @SerializedName("food_name")
        var foodName: String? = null
        ,
        @SerializedName("food_type")
        var foodType: String? = null
        ,
        @SerializedName("brand_name")
        var brandName: String? = null,
        @SerializedName("food_url")
        var foodUrl: String? = null
        ,
        @SerializedName("food_description")
        var foodDescription: String? = null
)