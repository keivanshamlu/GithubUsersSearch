package com.shamlou.search.ui.search

import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.shamlou.bases_android.fragment.BaseFragment
import com.shamlou.core.assisted.ViewModelFactory
import com.shamlou.search.R
import com.shamlou.search.databinding.FragmentSearchBinding
import com.shamlou.search.ui.search.adapter.LoaderSearchItemsAdapter
import com.shamlou.search.ui.search.adapter.LoaderStateAdapter
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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

    fun TextInputEditText.textInputAsFlow() = callbackFlow {
        val watcher: TextWatcher = doOnTextChanged { textInput: CharSequence?, _, _, _ ->
            trySend(textInput)
        }
        awaitClose { this@textInputAsFlow.removeTextChangedListener(watcher) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = LoaderSearchItemsAdapter().apply {

            //handles item click
            onItemClicked = { clickedItem, imageView ->

            }
            //checks initial loading
            addLoadStateListener { loadState ->
//                binding?.progressBarLoading?.isVisible =
//                    loadState.source.refresh is LoadState.Loading
//
//                // to handle error
//                viewModel.handlePagingError(loadState.getErrorState()?.error)
                if(loadState.source.refresh is LoadState.Error){
                    Log.d("TESTEST", (loadState.source.refresh as? LoadState.Error)?.error?.message?:"")
                }
            }
            //handles loading and retry
            loaderStateAdapter = LoaderStateAdapter {
                adapter.retry()
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.editTextSearchBar
            ?.textInputAsFlow()
            ?.debounce(800)
            ?.onEach {
                viewModel.searchUser(it.toString())?.let {
                    lifecycleScope.launch {
                        repeatOnLifecycle(Lifecycle.State.STARTED) {
                            it.collectLatest {
                                it?.let { adapter.submitData(it) }
                            }
                        }
                    }
                }
            }
            ?.launchIn(lifecycleScope)

        binding?.recyclerViewSearchResult?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerViewSearchResult?.adapter = adapter.withLoadStateFooter(loaderStateAdapter)
    }
}