package com.shamlou.bases_android.useCase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface UseCaseBaseFlow<P, R> {
    operator fun invoke(parameters: P): Flow<R> {
        return execute(parameters)
            .flowOn(Dispatchers.IO)
    }

    fun execute(parameters: P): Flow<R>
}
