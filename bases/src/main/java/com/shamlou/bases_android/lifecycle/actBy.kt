package com.shamlou.bases_android.lifecycle

import android.app.Activity
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.shamlou.bases_android.context.showSnackBar
import com.shamlou.bases_android.context.showToast
import com.shamlou.navigation.command.ActionCommand

fun LifecycleOwner.actBy(command: ActionCommand) {
    when (command) {
        is ActionCommand.ShowToast ->
            when (this) {
                is Activity -> showToast(command.message, command.duration)
                is Fragment -> showToast(command.message, command.duration)
            }

        is ActionCommand.ShowToastRes ->
            when (this) {
                is Activity -> showToast(command.message, command.duration)
                is Fragment -> showToast(command.message, command.duration)
            }

        is ActionCommand.ShowSnackBar ->
            when (this) {
                is Fragment ->
                    showSnackBar(command.message, command.duration)
                is Activity ->
                    findViewById<ViewGroup>(android.R.id.content)
                        .showSnackBar(command.message, command.duration)
            }

        is ActionCommand.ShowSnackBarRes ->
            when (this) {
                is Fragment ->
                    showSnackBar(command.message, command.duration)
                is Activity ->
                    findViewById<ViewGroup>(android.R.id.content)
                        .showSnackBar(command.message, command.duration)
            }
    }
}