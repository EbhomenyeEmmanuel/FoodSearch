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
                       @QueryMap user : Map<String, String>): FoodModelClass
    // FoodModel is POJO class to get the data from API, In above method I used Call<FoodModelClass>
    // because the data in our API is starting from JSONArray
    // and callback is used to get the response from api and it will set it in our POJO class
}