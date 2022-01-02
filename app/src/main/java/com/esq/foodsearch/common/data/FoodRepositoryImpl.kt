package com.esq.foodsearch.common.data

import com.esq.foodsearch.common.data.api.ApiParameters
import com.esq.foodsearch.common.data.api.FatSecretApi
import com.esq.foodsearch.common.data.api.model.mappers.ApiPaginationMapper
import com.esq.foodsearch.common.data.cache.Cache
import com.esq.foodsearch.common.data.cache.model.cachedfood.CachedFood
import com.esq.foodsearch.common.domain.model.food.Food
import com.esq.foodsearch.common.domain.repositories.FoodRepository
import com.esq.foodsearch.common.domain.model.pagination.PaginatedFoods
import com.esq.foodsearch.common.domain.model.search.SearchParameter
import io.reactivex.Flowable
import javax.inject.Inject

class FoodRepositoryImpl
@Inject constructor(
    private val api: FatSecretApi,
    private val cache: Cache,
    private val apiPaginationMapper: ApiPaginationMapper
) : FoodRepository {

    private val TAG: String? = FoodRepositoryImpl::class.simpleName

    override fun getFoods(): Flowable<List<Food>> {
        return cache.getListOfFoodsFromDb().distinctUntilChanged().map { foodList ->
            foodList.map { cachedFood ->
                cachedFood.toDomain(cachedFood)
            }
        }
    }

    override suspend fun requestFoodsUsingSearchKey(searchParameter: SearchParameter): PaginatedFoods {
        val fieldMaps = mutableMapOf<String, String>()
        val maxResults = if (searchParameter.maxResults < 50) searchParameter.maxResults else 50
        fieldMaps[ApiParameters.SEARCH_METHOD] = searchParameter.searchMethod
        fieldMaps[ApiParameters.SEARCH_EXPRESSION] = searchParameter.searchExpression
        fieldMaps[ApiParameters.SEARCH_FORMAT] = searchParameter.searchFormat
        val paginatedFood = api.getFoodsFromServer(fieldMaps, maxResults).apiPaginatedFoods
        return apiPaginationMapper.mapToDomain(paginatedFood)
    }

    override suspend fun storeFoods(food: List<Food>) {
        val foodCache = food.map {
            CachedFood.fromDomain(it)
        }
        return cache.storeFoodsToDb(foodCache)
    }

}