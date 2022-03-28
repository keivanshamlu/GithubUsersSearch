package com.shamlou.search.ui.userDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.shamlou.bases_android.useCase.Resource
import com.shamlou.bases_android.useCase.UseCaseBaseFlowResource
import com.shamlou.bases_android.viewModel.BaseViewModel
import com.shamlou.core.assisted.AssistedSavedStateViewModelFactory
import com.shamlou.domain.model.userDetails.ResponseUserDetailDomain
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

const val USER_NAME = "USER_NAME"
class UserDetailsViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val getUserDetailUseCase : UseCaseBaseFlowResource<String, ResponseUserDetailDomain>
) : BaseViewModel() {


    // holds userDetail data, mutable var
    // is private so we prevent reference scaping
    private val _userDetails =
        MutableStateFlow<Resource<ResponseUserDetailDomain>>(Resource.loading())
    val userDetails: StateFlow<Resource<ResponseUserDetailDomain>>
        get() = _userDetails

    // listen to savedStateHandle at the beginning
    init {

        getUserDetails()
    }

    // called in onCreate to pass out userName
    // I save this kind of variables into
    // savedStateHandle so it can survive memory
    // pressure as well as configure changes
    fun setUserName(userName: String){

        savedStateHandle.set(USER_NAME, userName)
    }

    //listens to savedStateHandle changes and if
    // there was new item emitted, calls use case
    // to provide presentation data, I used
    // distinctUntilChanged to prevent duplicate
    // calls in configure changes
    private fun getUserDetails() = viewModelScope.launch {

        savedStateHandle.getLiveData<String>(USER_NAME)
            .asFlow()
            .distinctUntilChanged()
            .onEach {
                _userDetails.tryEmit(Resource.loading())
                getUserDetailUseCase.execute(it)
                    .collect {
                        _userDetails.emit(it)
                        handleNetworkError(it)
                    }
            }.launchIn(viewModelScope)

    }

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<UserDetailsViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): UserDetailsViewModel
    }
}