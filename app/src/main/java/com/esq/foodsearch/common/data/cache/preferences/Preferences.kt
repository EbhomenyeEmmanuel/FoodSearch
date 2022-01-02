package com.esq.foodsearch.common.data.cache.preferences

interface Preferences {

  fun putToken(token: String)

  fun putTokenExpirationTime(time: Long)

  fun putTokenType(tokenType: String)

  fun getToken(): String

  fun getTokenExpirationTime(): Long

  fun getTokenType(): String

  fun deleteTokenInfo()
}