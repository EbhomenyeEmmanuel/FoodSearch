package com.esq.foodsearch;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FatSecretApiInterface {
    // API's endpoints
    @FormUrlEncoded
    @POST("https://oauth.fatsecret.com/connect/token")//Access token URL
    Call<UserTokenModel> login(@Field("grant_type") String grantType,
                               @Field("client_id") String username,
                               @Field("client_secret") String password,
                               @Field("scope") String scope);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("https://platform.fatsecret.com/rest/server.api?")
    Call<FoodModelClass> getResultsList(@Header("Authorization") String authToken,
                                        @Query("method") String method,
                                        @Query("search_expression") String searchKey,
                                        @Query("format") String format);
    // FoodModel is POJO class to get the data from API, In above method I used Call<FoodModelClass>
    // because the data in our API is starting from JSONArray
    // and callback is used to get the response from api and it will set it in our POJO class
}
