package com.esq.foodsearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FatSecretApiInterface {
    // API's endpoints
    @FormUrlEncoded
    @POST("https://oauth.fatsecret.com/connect/token")
    Call<UserTokenModel> login(@Field("grant_type") String grantType,
                               @Field("client_id") String username,
                               @Field("client_secret") String password,
                               @Field("scope") String scope);

    @POST("method=foods.search")//&search_expression={search_expression}&format=json
    Call<List<SearchListResponseModel>> getResultsList(@Header("Authorization") String authToken,
                                                       @Query("format") String format,
                                                       @Query("search_expression") String searchKey);
    // SearchListResponseModel is POJO class to get the data from API, In above method we use List<SearchListResponseModel>
    // because the data in our API is starting from JSONArray
    // and callback is used to get the response from api and it will set it in our POJO class
}
