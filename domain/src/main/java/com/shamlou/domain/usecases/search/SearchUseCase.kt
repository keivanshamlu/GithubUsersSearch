package com.shamlou.domain.usecases.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.shamlou.bases_android.useCase.Resource
import com.shamlou.bases_android.useCase.UseCaseBaseLiveData
import com.shamlou.domain.model.search.ResponseSearchDomain
import com.shamlou.domain.repo.SearchRepository

import javax.inject.Inject


class SearchUseCase @Inject constructor(
    private val repository: SearchRepository
) : UseCaseBaseLiveData<String, ResponseSearchDomain>() {

    override suspend fun invoke(param: String): LiveData<Resource<ResponseSearchDomain>> {
        return Transformations.map(repository.search(param)) {
            it // Place here your specific logic actions
        }
    }
}