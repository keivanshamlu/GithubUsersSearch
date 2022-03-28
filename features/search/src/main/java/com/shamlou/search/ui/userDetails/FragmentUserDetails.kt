package com.shamlou.search.ui.userDetails

import androidx.fragment.app.viewModels
import com.shamlou.bases_android.fragment.BaseFragment
import com.shamlou.core.assisted.ViewModelFactory
import com.shamlou.search.R
import com.shamlou.search.databinding.FragmentUserDetailsBinding
import javax.inject.Inject

class FragmentUserDetails : BaseFragment<UserDetailsViewModel, FragmentUserDetailsBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    override val layoutRes: Int = R.layout.fragment_user_details

    override val viewModel by viewModels<UserDetailsViewModel> {
        viewModelFactory.create(this, arguments)
    }

    override fun hookVariables() {
        binding?.viewModel = viewModel
    }
}