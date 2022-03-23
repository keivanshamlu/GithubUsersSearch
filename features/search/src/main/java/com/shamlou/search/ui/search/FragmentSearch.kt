package com.shamlou.search.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.shamlou.bases_android.fragment.BaseFragment
import com.shamlou.core.assisted.ViewModelFactory
import com.shamlou.search.R
import com.shamlou.search.databinding.FragmentSearchBinding
import com.shamlou.search.ui.search.search.AdapterSearch
import javax.inject.Inject

class FragmentSearch : BaseFragment<SearchViewModel, FragmentSearchBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    override val layoutRes: Int = R.layout.fragment_search

    override val viewModel by viewModels<SearchViewModel> {
        viewModelFactory.create(this, arguments)
    }
    override fun hookVariables() {
        binding?.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}