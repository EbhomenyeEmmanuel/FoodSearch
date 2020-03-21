package com.esq.foodsearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Foods {
    @SerializedName("food")
    private ArrayList<Food> food = null;
    @SerializedName("max_results")
    private String maxResults;
    @SerializedName("page_number")
    private String pageNumber;
    @SerializedName("total_results")
    private String totalResults;

    public ArrayList<Food> getFood() {
        return food;
    }

    public void setFood(ArrayList<Food> food) {
        this.food = food;
    }

    public String getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(String maxResults) {
        this.maxResults = maxResults;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }
}
