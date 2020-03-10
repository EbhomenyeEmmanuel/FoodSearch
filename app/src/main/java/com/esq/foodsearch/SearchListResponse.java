package com.esq.foodsearch;


import com.google.gson.annotations.SerializedName;

public class SearchListResponse {

    @SerializedName("food_id")
    private String id;

    @SerializedName("food_name")
    private String name;

    @SerializedName("food_type")
    private String type;

    @SerializedName("brand_name")
    private String brandName;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getBrandName() {
        return brandName;
    }

}
