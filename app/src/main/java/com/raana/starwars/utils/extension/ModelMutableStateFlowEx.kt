package com.raana.starwars.utils.extension


import com.raana.starwars.base.ModelWrapper
import com.raana.starwars.data.base.response.exception.AppException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

fun <T> MutableStateFlow<ModelWrapper<T>>.success(value: T) = update { ModelWrapper.Success(value) }

fun <T> MutableStateFlow<ModelWrapper<T>>.loading() = update { ModelWrapper.Loading() }

fun <T> MutableStateFlow<ModelWrapper<T>>.none() = update { ModelWrapper.None() }

fun <T> MutableStateFlow<ModelWrapper<T>>.failure(throwable: Throwable) = update {
    if (throwable is AppException) {
        ModelWrapper.Failure(info = throwable.info, throwable = throwable)
    } else {
        ModelWrapper.Failure(throwable = throwable)
    }
}