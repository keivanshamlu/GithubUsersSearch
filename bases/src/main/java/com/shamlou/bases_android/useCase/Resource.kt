package com.shamlou.bases_android.useCase

import com.shamlou.bases_android.mapper.Mapper


data class Resource<out T>(val status: Status, val data: T?, val error: Throwable?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(error: Throwable?, data: T?=null): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                error
            )
        }

        fun <T> loading(data: T?=null): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }

    fun isSuccess() = status == Status.SUCCESS
    fun isLoading() = status == Status.LOADING
    fun isError() = status == Status.ERROR
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}

fun <R, T> Resource<R>.map(mapper: Mapper<R, T>): Resource<T> {
    return when {
        this.isSuccess() -> {
            Resource.success(data?.let { mapper.map((it)) })
        }
        this.isLoading() -> {
            Resource.loading()
        }
        else -> {
            Resource.error(error,)
        }
    }
}
