package com.esq.foodsearch.api

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


/*
 *Api is a class in which we define a method getClient() that returns the API Interface class object
 */
object Api {
    private var retrofit: Retrofit? = null
    private val logInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(logInterceptor)
            .build()
    var gson = GsonBuilder().setLenient().create()

    //Return an initialized object of the FatSecretApiInterface
    val client: FatSecretApiInterface
        get() {
            if (retrofit == null) {
                //Build retrofit using the API's base URL
                retrofit = Retrofit.Builder()
                        .baseUrl("https://platform.fatsecret.com/rest/server.api/") //Base URL as indicated in the API website
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addCallAdapterFactory(CoroutineCallAdapterFactory())
                        .client(httpClient)
                        .build()
            }

            //Creating object for our interface
            return retrofit!!.create(FatSecretApiInterface::class.java) // return the APIInterface object
        }
}