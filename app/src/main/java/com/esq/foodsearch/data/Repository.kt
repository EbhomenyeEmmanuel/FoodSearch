package com.esq.foodsearch.data

import android.content.Context
import android.util.Log
import com.esq.foodsearch.api.Api
import com.esq.foodsearch.api.FatSecretApiInterface
import com.esq.foodsearch.model.FoodModelClass
import com.esq.foodsearch.model.UserTokenModel
import com.esq.foodsearch.utils.Constants
import io.paperdb.Paper

class Repository {
    private val TAG: String? = Repository::class.simpleName
    var client: FatSecretApiInterface = Api.client
    suspend fun verifyToken(grantType: String,
                            clientID: String,
                            clientSecret: String,
                            scope: String): UserTokenModel {
        val fieldMaps = mutableMapOf<String, String>()
        fieldMaps["grant_type"] = grantType
        fieldMaps["client_id"] = clientID
        fieldMaps["client_secret"] = clientSecret
        fieldMaps["scope"] = scope
        return client.login(fieldMaps)
    }

    suspend fun getSearchList(authToken: String, method: String = "foods.search", maxResults: Int = 40, searchExpression: String, format: String = "json"):
            FoodModelClass? {
        val fieldMaps = mutableMapOf<String, String>()
        fieldMaps["method"] = method
        fieldMaps["search_expression"] = searchExpression
        fieldMaps["format"] = format
        return client.getResultsList(authToken, fieldMaps, maxResults)
    }

    //Get saved clicked item
    fun getCredentialResponse(context: Context): UserTokenModel? {
        Paper.init(context)
        val item = Paper.book().read<UserTokenModel>(Constants.CREDENTIALS_RESPONSE)
        Log.d(TAG, "getClickedItem: Clicked item is $item")
        return item
    }

}