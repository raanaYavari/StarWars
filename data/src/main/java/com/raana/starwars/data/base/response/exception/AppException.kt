package com.raana.starwars.data.base.response.exception

import java.lang.Exception


/**
 * App exception
 *
 * @property errors
 * @property info
 * @constructor Create empty App exception
 */
data class AppException(val errors: List<String> = emptyList(), val info: Any? = null) :
    Exception()