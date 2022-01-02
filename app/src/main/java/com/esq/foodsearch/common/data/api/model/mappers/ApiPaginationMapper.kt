package com.esq.foodsearch.common.data.api.model.mappers

import com.esq.foodsearch.common.data.api.model.ApiPaginatedFoods
import com.esq.foodsearch.common.domain.model.pagination.PaginatedFoods
import com.esq.foodsearch.common.domain.model.pagination.Pagination
import javax.inject.Inject

class ApiPaginationMapper @Inject constructor(private val apiFoodMapper: ApiFoodMapper) :
    ApiMapper<ApiPaginatedFoods?, PaginatedFoods> {

    override fun mapToDomain(apiEntity: ApiPaginatedFoods?): PaginatedFoods {
        return PaginatedFoods(
            foods = apiFoodMapper.mapToDomainList(apiEntity?.apiFoods.orEmpty()),
            pagination = Pagination(
                apiEntity?.pageNumber?.toInt() ?: 0,
                apiEntity?.totalResults?.toInt() ?: 0
            )
        )
    }
}