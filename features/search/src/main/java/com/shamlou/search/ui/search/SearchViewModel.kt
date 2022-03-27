package com.shamlou.search.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shamlou.bases_android.useCase.UseCaseBaseFlow
import com.shamlou.bases_android.viewModel.BaseViewModel
import com.shamlou.core.assisted.AssistedSavedStateViewModelFactory
import com.shamlou.domain.model.search.ResponseItemsDomain
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

const val LAST_QUERY = "LAST_QUERY"

class SearchViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val getSearchUseCase: UseCaseBaseFlow<String, PagingData<ResponseItemsDomain>>,
) : BaseViewModel() {


    var search: StateFlow<PagingData<ResponseItemsDomain>?>? = null


    fun searchUser(query: String) : StateFlow<PagingData<ResponseItemsDomain>?>? {
        if (query.isEmpty()) return search
        if (search == null || savedStateHandle.get<String>(LAST_QUERY) != query) {
            savedStateHandle.set(LAST_QUERY, query)
            search = getSearchUseCase(query)
                .cachedIn(viewModelScope)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
                    initialValue = null
                )
        }
        return search
    }

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<SearchViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): SearchViewModel
    }
}