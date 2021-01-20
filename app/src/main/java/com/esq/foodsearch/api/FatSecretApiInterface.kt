package com.esq.foodsearch.api

import com.esq.foodsearch.model.FoodModelClass
import com.esq.foodsearch.model.UserTokenModel
import retrofit2.Call
import retrofit2.http.*

interface FatSecretApiInterface {
    // API's endpoints
    @FormUrlEncoded
    @POST("https://oauth.fatsecret.com/connect/token")
    suspend fun  //Access token URL
            login(@FieldMap user : Map<String, String>): UserTokenModel

    @GET("https://platform.fatsecret.com/rest/server.api?")
    suspend fun getResultsList(@Header("Authorization") authToken: String?,
                       @QueryMap user : Map<String, String>, @Query("max_results")maxResult: Int): FoodModelClass
}