package com.esq.foodsearch;

import com.google.gson.annotations.SerializedName;

public class UserTokenModel {
    @SerializedName("access_token")
    private String token;
    @SerializedName("expires_in")
    private int tokenExpirationSeconds;

    public String getToken() {
        return token;
    }

    public int getTokenExpirationSeconds() {
        return tokenExpirationSeconds;
    }
}
