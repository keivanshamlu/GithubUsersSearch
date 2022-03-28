package com.shamlou

import com.shamlou.bases_android.useCase.Resource
import com.shamlou.bases_android.useCase.UseCaseBaseFlowResource
import com.shamlou.domain.model.userDetails.ResponseUserDetailDomain
import com.shamlou.search.ResponseSearchFakers.sampleError
import com.shamlou.search.ResponseSearchFakers.validResponseUserDetailDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// since mockk does not support stubbing for base classes,
// i created this fake classes so i can stub execute()
class UseCaseGetUserDetailUseCaseSuccess :
    UseCaseBaseFlowResource<String, ResponseUserDetailDomain> {

    override fun execute(parameters: String): Flow<Resource<ResponseUserDetailDomain>> {
        return flow { emit(Resource.success(validResponseUserDetailDomain)) }
    }

    override fun invoke(parameters: String): Flow<Resource<ResponseUserDetailDomain>> {
        return flow { emit(Resource.success(validResponseUserDetailDomain)) }
    }
}

class UseCaseGetUserDetailUseCaseFailure : UseCaseBaseFlowResource<String, ResponseUserDetailDomain> {
    override fun execute(parameters: String): Flow<Resource<ResponseUserDetailDomain>> =
        flow { emit(Resource.error(sampleError)) }

    override fun invoke(parameters: String): Flow<Resource<ResponseUserDetailDomain>> =
        flow { emit(Resource.error(sampleError)) }

}
