package com.esq.foodsearch.common.domain.model.search

import com.esq.foodsearch.common.data.api.ApiParameters

/*
 *Class for needed search parameters used to get data from Api
 */
data class SearchParameter(
    val searchFormat: String = ApiParameters.SEARCH_FORMAT_VALUE,
    val searchMethod: String = ApiParameters.SEARCH_METHOD_VALUE,
    val pageToLoad: Int = 0,
    val maxResults: Int = 20,
    val searchExpression: String,
)