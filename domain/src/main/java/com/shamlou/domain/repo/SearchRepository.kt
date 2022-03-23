package com.shamlou.domain.repo

import androidx.lifecycle.LiveData
import com.shamlou.bases_android.useCase.Resource
import com.shamlou.domain.model.search.ResponseSearchDomain

interface SearchRepository {

    fun search(param: String): LiveData<Resource<ResponseSearchDomain>>
}