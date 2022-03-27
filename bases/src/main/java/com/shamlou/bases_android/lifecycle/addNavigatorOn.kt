package com.shamlou.bases_android.lifecycle


import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.shamlou.bases_android.viewModel.BaseViewModel
import com.shamlou.navigation.command.navigateBy
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * observes navigationCommand from ViewModelBase
 */
fun LifecycleOwner.addNavigatorOn(
    viewModel: BaseViewModel,
    navController: NavController
) {

    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.navigationCommand.collect { command ->
                command?.getContentIfNotHandled()?.let {
                    navController.navigateBy(it)
                }
            }
        }
    }
}

fun LifecycleOwner.observeActions(
    viewModel: BaseViewModel
) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.actionCommand.collect { command ->
                command?.getContentIfNotHandled()?.let {
                    actBy(it)
                }
            }
        }
    }
}
