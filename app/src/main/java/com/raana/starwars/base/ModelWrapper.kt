package com.raana.starwars.base

sealed class ModelWrapper<T> {
    abstract val data: T?
    class Loading<T> : ModelWrapper<T>() {
        override val data: T?
            get() = null
    }

    data class Success<T>(val result: T) : ModelWrapper<T>() {
        private var isHandled = false
        fun getResultIfNotHandled(): T? {
            if (isHandled) return null
            isHandled = true
            return result
        }

        override val data: T?
            get() = result
    }
    data class Failure<T>(val info: Any? = null, val throwable: Throwable) : ModelWrapper<T>() {
        private var isHandled = false
        fun getResultIfNotHandled() : Boolean? {
            if (isHandled) return null
            isHandled = true
            return true
        }

        override val data: T?
            get() = null
    }
    class None<T> : ModelWrapper<T>() {
        override val data: T?
            get() = null
    }
}