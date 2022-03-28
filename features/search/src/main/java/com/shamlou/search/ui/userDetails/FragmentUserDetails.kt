package com.shamlou.search.ui.userDetails

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.shamlou.bases_android.fragment.BaseFragment
import com.shamlou.core.assisted.ViewModelFactory
import com.shamlou.search.R
import com.shamlou.search.databinding.FragmentUserDetailsBinding
import javax.inject.Inject

class FragmentUserDetails : BaseFragment<UserDetailsViewModel, FragmentUserDetailsBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    override val layoutRes: Int = R.layout.fragment_user_details

    val args: FragmentUserDetailsArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setUserName(args.userName)
    }

    override val viewModel by viewModels<UserDetailsViewModel> {
        viewModelFactory.create(this, arguments)
    }

    override fun hookVariables() {
        binding?.viewModel = viewModel
    }
}