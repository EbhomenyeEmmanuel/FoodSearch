package com.esq.foodsearch.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory;
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
/*
 *Api is a class in which we define a method getClient() that returns the API Interface class object
 */

public class Api {
    private static Retrofit retrofit = null;
    private static HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor().
            setLevel(HttpLoggingInterceptor.Level.BODY);

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(logInterceptor);

    //Return an initialized object of the FatSecretApiInterface
    public static FatSecretApiInterface getClient() {
        if (retrofit == null) {
            //Build retrofit using the API's base URL
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://platform.fatsecret.com/rest/server.api/")//Base URL as indicated in the API website
                    .addConverterFactory(MoshiConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        //Creating object for our interface
        FatSecretApiInterface api = retrofit.create(FatSecretApiInterface.class);
        return api; // return the APIInterface object
    }

}
