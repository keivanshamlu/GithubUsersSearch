package com.shamlou.search.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shamlou.bases_android.useCase.UseCaseBaseFlow
import com.shamlou.bases_android.viewModel.BaseViewModel
import com.shamlou.core.assisted.AssistedSavedStateViewModelFactory
import com.shamlou.domain.model.search.ResponseItemsDomain
import com.shamlou.navigation.command.NavigationFlow
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

    // contains pagedData that holds search results
    private var search: StateFlow<PagingData<ResponseItemsDomain>?>? = null

    // called when search bar changes, debounce
    // operation is already set on edittext so
    // we don't have to be worried about repetitive
    // query calls, checks whether query is different
    // from last searched query (happens in configure
    // changes) to prevent losing state in recyclerview,
    // when query was different, calls use case to search
    // and fetch new paged items, [search] only holds
    // data to prevent misbehavior's in configure changes,
    // I return flow and I collect new flow each time
    // a new query was fetched
    fun searchUser(query: String): StateFlow<PagingData<ResponseItemsDomain>?>? {
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

    fun navigateToUserDetails(user: ResponseItemsDomain){

        navigateTo(NavigationFlow.ToUserDetails(user.login))
    }

    // called by fragment, paging error happens
    fun handlePagingError(error: Throwable?) {

        error?.let { handlePagingErrorSnackBar(it) }
    }

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<SearchViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): SearchViewModel
    }
}