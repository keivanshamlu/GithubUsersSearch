package com.shamlou.bases_android.useCase

import androidx.lifecycle.LiveData

abstract class UseCaseBaseLiveData<Parameter, Response> {
    abstract suspend operator fun invoke(param: Parameter): LiveData<Resource<Response>>
}