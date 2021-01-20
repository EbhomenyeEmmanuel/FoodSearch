package com.esq.foodsearch.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.esq.foodsearch.data.Repository
import com.esq.foodsearch.model.FoodModelClass
import com.esq.foodsearch.model.UserTokenModel
import com.esq.foodsearch.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketException

class MainActivityViewModel(application: Application, private val repo: Repository = Repository()) : AndroidViewModel(application) {

    private val context = application.applicationContext
    private val authToken: String? = getResponse()?.token

    fun getSearchList(searchKey: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val response = repo.getSearchList(authToken = "Bearer $authToken", searchExpression = searchKey)
            emit(Resource.success(response))
        } catch (e: IOException) {
            emit(Resource.error(e.message!!, data = null))
        }
    }

    private fun getResponse(): UserTokenModel? {
        return repo.getCredentialResponse(context = context)
    }

}