package com.esq.foodsearch.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.esq.foodsearch.data.Repository
import com.esq.foodsearch.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class VerifyUserTokenActivityViewModel(private val repo: Repository = Repository()) : ViewModel() {

    private val TAG = this::class.java.simpleName
    private var grantType: String = "client_credentials"
    private var clientId: String = "15763421aecc458294ee304cd7cab984"
    private var clientSecret: String = "5ed557eaca4b4307a7cd21dc506a088f"
    private var scope: String = "basic"

    val tokenResponse = liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            try {
                Log.d(TAG, "try{}: Calling repo")
                val response = repo.verifyToken(grantType, clientId, clientSecret, scope)
                emit(Resource.success(response))
            } catch (e: IOException) {
                Log.d(TAG, "catch(): Error ${e.message}")
                emit(Resource.error(e.message!!, data = null))
            }
    }

}
