package com.esq.foodsearch.common.data.api.interceptors

import com.esq.foodsearch.common.data.api.ApiConstants
import com.esq.foodsearch.common.data.api.ApiConstants.AUTH_ENDPOINT
import com.esq.foodsearch.common.data.api.ApiConstants.KEY
import com.esq.foodsearch.common.data.api.ApiConstants.SECRET
import com.esq.foodsearch.common.data.api.ApiParameters.AUTH_HEADER
import com.esq.foodsearch.common.data.api.ApiParameters.CLIENT_ID
import com.esq.foodsearch.common.data.api.ApiParameters.CLIENT_SECRET
import com.esq.foodsearch.common.data.api.ApiParameters.GRANT_TYPE_KEY
import com.esq.foodsearch.common.data.api.ApiParameters.GRANT_TYPE_VALUE
import com.esq.foodsearch.common.data.api.ApiParameters.SCOPE_KEY
import com.esq.foodsearch.common.data.api.ApiParameters.SCOPE_VALUE
import com.esq.foodsearch.common.data.api.ApiParameters.TOKEN_TYPE
import com.esq.foodsearch.common.data.api.model.ApiToken
import com.esq.foodsearch.common.data.cache.preferences.Preferences
import com.squareup.moshi.Moshi
import okhttp3.*
import org.threeten.bp.Instant
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(
    private val preferences: Preferences
) : Interceptor {

    companion object {
        const val UNAUTHORIZED = 401
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = preferences.getToken() // 1
        val tokenExpirationTime = Instant.ofEpochSecond(preferences.getTokenExpirationTime())
        val request = chain.request()
// if (chain.request().headers[NO_AUTH_HEADER] != null) return chain.proceed(request) // 4

        val interceptedRequest: Request // 1
        if (tokenExpirationTime.isAfter(Instant.now())) { //For Valid tokens
            interceptedRequest = chain.createAuthenticatedRequest(token)
        } else { //Handling invalid tokens
            val tokenRefreshResponse = chain.refreshToken()
            interceptedRequest = if (tokenRefreshResponse.isSuccessful) {
                val newToken = mapToken(tokenRefreshResponse)
                if (newToken.isValid()) {
                    storeNewToken(newToken)
                    chain.createAuthenticatedRequest(newToken.accessToken!!)
                } else {
                    request
                }
            } else {
                request
            }
        }
        return chain.proceedDeletingTokenIfUnauthorized(interceptedRequest) // 3

    }

    private fun Interceptor.Chain.createAuthenticatedRequest(token: String): Request {
        return request()
            .newBuilder()
            .addHeader(AUTH_HEADER, TOKEN_TYPE + token)
            .build()
    }

    private fun Interceptor.Chain.refreshToken(): Response {
        val url = request()
            .url
            .newBuilder(AUTH_ENDPOINT)!!
            .build()

        val body = FormBody.Builder()
            .add(GRANT_TYPE_KEY, GRANT_TYPE_VALUE)
            .add(CLIENT_ID, KEY)
            .add(CLIENT_SECRET, SECRET)
            .add(SCOPE_KEY, SCOPE_VALUE)
            .build()

        val tokenRefresh = request()
            .newBuilder()
            .post(body)
            .url(url)
            .build()

        return proceedDeletingTokenIfUnauthorized(tokenRefresh)
    }

    private fun Interceptor.Chain.proceedDeletingTokenIfUnauthorized(request: Request): Response {
        val response = proceed(request)

        if (response.code == UNAUTHORIZED) {
            preferences.deleteTokenInfo()
        }

        return response
    }

    private fun mapToken(tokenRefreshResponse: Response): ApiToken {
        val moshi = Moshi.Builder().build()
        val tokenAdapter = moshi.adapter<ApiToken>(ApiToken::class.java)
        val responseBody = tokenRefreshResponse.body!! // if successful, this should be good :]
        return tokenAdapter.fromJson(responseBody.string()) ?: ApiToken.INVALID
    }

    private fun storeNewToken(apiToken: ApiToken) {
        with(preferences) {
            putTokenType(apiToken.tokenType!!)
            putTokenExpirationTime(apiToken.expiresAt)
            putToken(apiToken.accessToken!!)
        }
    }
}
