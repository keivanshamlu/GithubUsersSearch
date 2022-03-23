package com.shamlou.navigation.command


/**
 * using command design pattern to encapsulate info of navigation actions
 */
sealed class NavigationCommand {
    data class To(val flow: NavigationFlow) : NavigationCommand()
    object Back : NavigationCommand()
    data class BackTo(val destinationId: Int, val inclusive: Boolean) : NavigationCommand()
}