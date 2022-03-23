package com.shamlou.navigation.command

import androidx.annotation.StringRes

/**
 * using command design pattern to encapsulate info of actions like toast, snackbar etc...
 */
sealed class ActionCommand {
    data class ShowToast(val message: String, val duration: Int) : ActionCommand()
    data class ShowToastRes(@StringRes val message: Int, val duration: Int) : ActionCommand()
    data class ShowSnackBar(val message: String, val duration: Int) : ActionCommand()
    data class ShowSnackBarRes(@StringRes val message: Int, val duration: Int) : ActionCommand()
}