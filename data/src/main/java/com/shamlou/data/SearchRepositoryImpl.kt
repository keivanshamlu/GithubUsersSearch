package com.shamlou.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.shamlou.bases_android.mapper.Mapper
import com.shamlou.bases_android.useCase.Resource
import com.shamlou.data.model.search.ResponseSearchRemote
import com.shamlou.data.services.SearchApi
import com.shamlou.domain.model.search.ResponseSearchDomain
import com.shamlou.domain.repo.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchApi,
    private val mapperSearchRemoteToDomain : Mapper<ResponseSearchRemote ,ResponseSearchDomain>
) : SearchRepository {

    override fun search(param: String): LiveData<Resource<ResponseSearchDomain>> = liveData {
        emit(Resource.loading(null))
        try {
            val response = searchService.getPokemons(1,1)
            emit(Resource.success(mapperSearchRemoteToDomain.map(response)))
        } catch (exception: Throwable) {
            emit(Resource.error(exception, null))
        }
    }
}