package com.esq.foodsearch;

import com.google.gson.annotations.SerializedName;

public class FoodModelClass {
    @SerializedName("foods")
    private Foods foods;

    public Foods getFoods() {
        return foods;
    }

    public void setFoods(Foods foods) {
        this.foods = foods;
    }
}
