package com.shamlou.bases_android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import com.shamlou.bases_android.lifecycle.addNavigatorOn
import com.shamlou.bases_android.lifecycle.observeActions
import com.shamlou.bases_android.viewModel.BaseViewModel
import dagger.android.support.DaggerFragment

/**
 * to reuse shared logic between all fragments
 * like: prevent binding object from leaking,
 * handle navigation and ui actions like toast and
 * snack bar using command design pattern
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> :
    DaggerFragment() {

    abstract val viewModel: VM
    abstract val layoutRes: Int

    open var binding: DB? = null

    abstract fun hookVariables()

    open fun bindObservables() {}

    open fun oneTimeEvent() {}

    open fun everyTimeEvent() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavigator()
        initActions()
        bindObservables()
        oneTimeEvent()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        initDataBinding(inflater, container)
        return binding?.root ?: View(context)
    }

    override fun onStart() {
        super.onStart()
        everyTimeEvent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initActions() = observeActions(viewModel)

    private fun initNavigator() = addNavigatorOn(viewModel, findNavController())

    private fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        DataBindingUtil.inflate<DB>(inflater, layoutRes, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            executePendingBindings()
        }.also { binding = it }
        hookVariables()
    }
}