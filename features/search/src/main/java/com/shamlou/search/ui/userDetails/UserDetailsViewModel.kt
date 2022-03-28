package com.shamlou.search.ui.userDetails

import androidx.lifecycle.SavedStateHandle
import com.shamlou.bases_android.viewModel.BaseViewModel
import com.shamlou.core.assisted.AssistedSavedStateViewModelFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class UserDetailsViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {


    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<UserDetailsViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): UserDetailsViewModel
    }
}