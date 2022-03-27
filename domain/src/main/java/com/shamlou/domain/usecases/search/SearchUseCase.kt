package com.shamlou.domain.usecases.search

import androidx.paging.PagingData
import com.shamlou.bases_android.useCase.UseCaseBaseFlow
import com.shamlou.domain.model.search.ResponseItemsDomain
import com.shamlou.domain.repo.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * calls repository to search for a user and then returns
 * info as PagingData do it can be easily integrated with
 * presentation and we can use paging 3 advantages
 */
class SearchUseCase @Inject constructor(
    private val repository: SearchRepository
) : UseCaseBaseFlow<String, PagingData<ResponseItemsDomain>> {

    override fun execute(parameters: String): Flow<PagingData<ResponseItemsDomain>> {
        return repository.search(parameters).map {
            it
        }
    }
}