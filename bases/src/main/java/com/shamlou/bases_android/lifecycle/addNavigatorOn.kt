package com.shamlou.bases_android.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.shamlou.bases_android.viewModel.BaseViewModel
import com.shamlou.navigation.command.navigateBy

/**
 * observes navigationCommand from ViewModelBase
 */
fun LifecycleOwner.addNavigatorOn(
    viewModel: BaseViewModel,
    navController: NavController
) {

    viewModel.navigationCommand.observe(this) { command ->
        command?.getContentIfNotHandled()?.let {
            navController.navigateBy(it)
        }
    }
}

fun LifecycleOwner.observeActions(
    viewModel: BaseViewModel
) {
    viewModel.actionCommand.observe(this) { command ->
        command?.getContentIfNotHandled()?.let {
            actBy(it)
        }
    }
}
