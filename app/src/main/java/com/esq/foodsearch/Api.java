package com.esq.foodsearch;

import retrofit.RestAdapter;

public class Api {

    public static FatSecretApiInterface getClient() {

        // change your base URL
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("https://platform.fatsecret.com/rest/server.api/") //Set the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        FatSecretApiInterface api = adapter.create(FatSecretApiInterface.class);
        return api; // return the APIInterface object
    }

}
