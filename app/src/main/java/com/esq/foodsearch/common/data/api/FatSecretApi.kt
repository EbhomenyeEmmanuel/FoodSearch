package com.esq.foodsearch.common.data.api

import com.esq.foodsearch.common.data.api.ApiConstants.FOOD_ENDPOINT
import com.esq.foodsearch.common.data.api.ApiParameters.MAX_RESULTS
import com.esq.foodsearch.common.data.api.model.ApiFoodModelClass
import retrofit2.http.*

interface FatSecretApi {
    @GET(FOOD_ENDPOINT)
    suspend fun getFoodsFromServer(
        @QueryMap searchParams: Map<String, String>,
        @Query(MAX_RESULTS) maxResult: Int
    ): ApiFoodModelClass
}