package com.shamlou.bases_android.viewModel

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.shamlou.bases_android.useCase.Resource
import com.shamlou.bases_android.event.Event
import com.shamlou.navigation.command.ActionCommand
import com.shamlou.navigation.command.NavigationCommand
import com.shamlou.navigation.command.NavigationFlow

abstract class BaseViewModel : ViewModel() {

    private val _navigationCommand = MutableLiveData<Event<NavigationCommand>?>()
    val navigationCommand: LiveData<Event<NavigationCommand>?> = _navigationCommand

    fun navigateTo(flow: NavigationFlow) =
        _navigationCommand.postValue(Event(NavigationCommand.To(flow)))

    fun navigateBack() =
        _navigationCommand.postValue(Event(NavigationCommand.Back))

    fun navigateBackTo(destinationId: Int, inclusive: Boolean) =
        _navigationCommand.postValue(Event(NavigationCommand.BackTo(destinationId, inclusive)))


    private val _actionCommand = MutableLiveData<Event<ActionCommand>?>()
    val actionCommand: LiveData<Event<ActionCommand>?> = _actionCommand

    fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
        _actionCommand.postValue(Event(ActionCommand.ShowToast(message, duration)))


    fun showToast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) =
        _actionCommand.postValue(Event(ActionCommand.ShowToastRes(message, duration)))


    fun showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) =
        _actionCommand.postValue(Event(ActionCommand.ShowSnackBar(message, duration)))


    fun showSnackBar(@StringRes message: Int, duration: Int = Snackbar.LENGTH_SHORT) =
        _actionCommand.postValue(Event(ActionCommand.ShowSnackBarRes(message, duration)))

    fun handleNetworkError(it: Resource<*>) {
        if (it.status == Resource.Status.ERROR) _actionCommand.postValue(Event(ActionCommand.ShowSnackBar(it.error?.message?:"",Snackbar.LENGTH_LONG)))
    }
}