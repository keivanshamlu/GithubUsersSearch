package com.shamlou.search.ui.search

import androidx.lifecycle.SavedStateHandle
import com.shamlou.bases_android.useCase.UseCaseBaseLiveData
import com.shamlou.bases_android.viewModel.BaseViewModel
import com.shamlou.core.assisted.AssistedSavedStateViewModelFactory
import com.shamlou.domain.model.search.ResponseSearchDomain
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.flow.*

class SearchViewModel @AssistedInject constructor(@Assisted private val savedStateHandle: SavedStateHandle,
                                                  private val getSearchUseCase : UseCaseBaseLiveData<String, ResponseSearchDomain>
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<SearchViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): SearchViewModel
    }
}