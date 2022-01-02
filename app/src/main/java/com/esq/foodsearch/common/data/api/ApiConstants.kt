package com.esq.foodsearch.common.data.api

import com.esq.foodsearch.BuildConfig

object ApiConstants {
    const val BASE_ENDPOINT = "https://platform.fatsecret.com/rest/server.api/"
    const val AUTH_ENDPOINT = "https://oauth.fatsecret.com/connect/token"
    const val FOOD_ENDPOINT = "https://platform.fatsecret.com/rest/server.api"

    const val KEY = BuildConfig.ApiKey
    const val SECRET = BuildConfig.ApiSecret
}

object ApiParameters {
    const val TOKEN_TYPE = "Bearer "
    const val AUTH_HEADER = "Authorization"
    const val GRANT_TYPE_KEY = "grant_type"
    const val GRANT_TYPE_VALUE = "client_credentials"

    const val SCOPE_KEY = "scope"
    const val SCOPE_VALUE = "basic"
    const val CLIENT_ID = "client_id"
    const val CLIENT_SECRET = "client_secret"
    const val MAX_RESULTS = "max_results"

    const val SEARCH_METHOD = "method"
    const val SEARCH_METHOD_VALUE = "foods.search"
    const val SEARCH_EXPRESSION = "search_expression"
    const val SEARCH_FORMAT = "format"
    const val SEARCH_FORMAT_VALUE = "json"

}