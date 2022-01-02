package com.esq.foodsearch.common.domain.model

import java.io.IOException


class NoMoreFoodException(message: String): Exception(message)

class NetworkUnavailableException(message: String = "No network available :(") : IOException(message)