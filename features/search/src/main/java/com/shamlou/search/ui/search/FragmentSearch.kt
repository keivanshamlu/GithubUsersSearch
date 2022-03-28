package com.shamlou.search.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.shamlou.bases_android.edittext.textInputAsFlow
import com.shamlou.bases_android.fragment.BaseFragment
import com.shamlou.core.assisted.ViewModelFactory
import com.shamlou.search.R
import com.shamlou.search.databinding.FragmentSearchBinding
import com.shamlou.search.ui.search.adapter.LoaderSearchItemsAdapter
import com.shamlou.search.ui.search.adapter.LoaderStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class FragmentSearch : BaseFragment<SearchViewModel, FragmentSearchBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    override val layoutRes: Int = R.layout.fragment_search

    private lateinit var adapter: LoaderSearchItemsAdapter
    private lateinit var loaderStateAdapter: LoaderStateAdapter

    override val viewModel by viewModels<SearchViewModel> {
        viewModelFactory.create(this, arguments)
    }

    override fun hookVariables() {
        binding?.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {

        adapter = LoaderSearchItemsAdapter().apply {

            //handles item click
            onItemClicked = { clickedItem ->

                viewModel.navigateToUserDetails(clickedItem)
            }
            //checks initial loading
            addLoadStateListener { loadState ->
                binding?.progressBarLoading?.isVisible =
                    loadState.source.refresh is LoadState.Loading

                // to handle error
                if (loadState.source.refresh is LoadState.Error) {
                    viewModel.handlePagingError((loadState.source.refresh as? LoadState.Error)?.error)
                }
            }
            //handles loading and retry
            loaderStateAdapter = LoaderStateAdapter {
                adapter.retry()
            }
        }

    }

    private fun setUpViews() {

        // listens to recycler changes and applies
        // debounce on in to prevent repetitive api
        // calls and then calls view model and
        // observes it paged data
        binding?.editTextSearchBar
            ?.textInputAsFlow()
            ?.debounce(800)
            ?.onEach {
                viewModel.searchUser(it.toString().lowercase(Locale.getDefault()))?.let {
                    lifecycleScope.launch {
                        repeatOnLifecycle(Lifecycle.State.STARTED) {
                            it.collectLatest {
                                it?.let {
                                    adapter.submitData(it)
                                }
                            }
                        }
                    }
                }
            }
            ?.launchIn(lifecycleScope)

        binding?.recyclerViewSearchResult?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerViewSearchResult?.adapter = adapter.withLoadStateFooter(loaderStateAdapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
    }
}