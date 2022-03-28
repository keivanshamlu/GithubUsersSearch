package com.shamlou.domain.usecases.userDetails

import com.shamlou.bases_android.useCase.Resource
import com.shamlou.bases_android.useCase.UseCaseBaseFlowResource
import com.shamlou.domain.model.userDetails.ResponseUserDetailDomain
import com.shamlou.domain.repo.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * calls repository to get user details to be shown in UI
 */
class UserDetailUseCase @Inject constructor(
    private val repository: SearchRepository
) : UseCaseBaseFlowResource<String, ResponseUserDetailDomain> {

    override fun execute(parameters: String): Flow<Resource<ResponseUserDetailDomain>> {
        return repository.userDetails(parameters).map {
            Resource.success(it)
        }
    }
}