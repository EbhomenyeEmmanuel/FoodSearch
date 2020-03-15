package com.esq.foodsearch;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
 *Api is a class in which we define a method getClient() that returns the API Interface class object
 */

public class Api {
    private static Retrofit retrofit = null;
    //Return an initialized object of the FatSecretApiInterface
    public static FatSecretApiInterface getClient() {
        // change your base URL
        if ( retrofit == null) {
            //Build retrofit using the API's base URL
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://platform.fatsecret.com/rest/server.api/")//Base URL as indicated in the API website
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        //Creating object for our interface
        FatSecretApiInterface api = retrofit.create(FatSecretApiInterface.class);
        return api; // return the APIInterface object
    }

}
