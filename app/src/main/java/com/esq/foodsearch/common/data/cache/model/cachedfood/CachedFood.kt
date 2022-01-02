package com.esq.foodsearch.common.data.cache.model.cachedfood

import androidx.room.Entity
import com.esq.foodsearch.common.data.api.model.ApiFood
import com.esq.foodsearch.common.domain.model.food.Food

@Entity
data class CachedFood(
    val id: String? = null,
    val foodName: String? = null,
    val foodType: String? = null,
    val brandName: String? = null,
    val foodUrl: String? = null,
    val foodDescription: String? = null
) {
    companion object {
        fun fromDomain(domainEntity: Food): CachedFood {
            return CachedFood(
                id = domainEntity.id,
                foodName = domainEntity.foodName,
                foodType = domainEntity.foodType,
                brandName = domainEntity.brandName,
                foodUrl = domainEntity.foodUrl,
                foodDescription = domainEntity.foodDescription
            )
        }
    }

    fun toDomain(cacheEntity: CachedFood): Food {
        return Food(
            id = cacheEntity.id,
            foodName = cacheEntity.foodName,
            foodType = cacheEntity.foodType,
            brandName = cacheEntity.brandName,
            foodUrl = cacheEntity.foodUrl,
            foodDescription = cacheEntity.foodDescription
        )
    }
}