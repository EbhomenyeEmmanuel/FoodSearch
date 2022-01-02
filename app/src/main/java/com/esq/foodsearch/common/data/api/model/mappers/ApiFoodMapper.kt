package com.esq.foodsearch.common.data.api.model.mappers

import com.esq.foodsearch.common.data.api.model.ApiFood
import com.esq.foodsearch.common.domain.model.food.Food
import javax.inject.Inject

class ApiFoodMapper @Inject constructor() : ApiMapper<ApiFood?, Food> {

    override fun mapToDomain(apiEntity: ApiFood?): Food {
        return Food(
            id = apiEntity?.foodId.orEmpty(),
            foodName = apiEntity?.foodName.orEmpty(),
            foodType = apiEntity?.foodType.orEmpty(),
            brandName = apiEntity?.brandName.orEmpty(),
            foodUrl = apiEntity?.foodUrl.orEmpty(),
            foodDescription = apiEntity?.foodDescription.orEmpty()
        )
    }

    fun mapToDomainList(apiEntities: List<ApiFood?>): List<Food> {
        return apiEntities.map { mapToDomain(it) }
    }
}