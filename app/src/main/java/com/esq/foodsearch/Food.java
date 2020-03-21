package com.esq.foodsearch;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/*
 *Food class contain details about a specified food
 */
public class Food {

    @SerializedName("food_description")
    private String foodDescription;
    @SerializedName("food_id")
    private String foodId;
    @SerializedName("food_name")
    private String foodName;
    @SerializedName("food_type")
    private String foodType;
    @SerializedName("food_url")
    private String foodUrl;
    @SerializedName("brand_name")
    private String brandName;

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodUrl() {
        return foodUrl;
    }

    public void setFoodUrl(String foodUrl) {
        this.foodUrl = foodUrl;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
