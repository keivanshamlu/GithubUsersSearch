package com.shamlou.bases_android.viewModel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.shamlou.bases_android.event.Event
import com.shamlou.bases_android.useCase.Resource
import com.shamlou.navigation.command.ActionCommand
import com.shamlou.navigation.command.NavigationCommand
import com.shamlou.navigation.command.NavigationFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class BaseViewModel : ViewModel() {

    private val _navigationCommand = MutableSharedFlow<Event<NavigationCommand>?>(1)
    val navigationCommand: SharedFlow<Event<NavigationCommand>?> = _navigationCommand

    fun navigateTo(flow: NavigationFlow) =
        _navigationCommand.tryEmit(Event(NavigationCommand.To(flow)))

    fun navigateBack() =
        _navigationCommand.tryEmit(Event(NavigationCommand.Back))

    fun navigateBackTo(destinationId: Int, inclusive: Boolean) =
        _navigationCommand.tryEmit(Event(NavigationCommand.BackTo(destinationId, inclusive)))

    private val _actionCommand = MutableSharedFlow<Event<ActionCommand>?>(1)
    val actionCommand: SharedFlow<Event<ActionCommand>?> = _actionCommand

    fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
        _actionCommand.tryEmit(Event(ActionCommand.ShowToast(message, duration)))

    fun showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) =
        _actionCommand.tryEmit(Event(ActionCommand.ShowSnackBar(message, duration)))

    fun handleNetworkError(it: Resource<*>) {
        if (it.status == Resource.Status.ERROR) _actionCommand.tryEmit(Event(ActionCommand.ShowSnackBar(it.error?.message?:"",Snackbar.LENGTH_LONG)))
    }
    // show snack bar error to user
    fun handlePagingErrorSnackBar(t : Throwable) {
        _actionCommand.tryEmit(Event(ActionCommand.ShowSnackBar(t.message?:"",Snackbar.LENGTH_LONG)))
    }
}