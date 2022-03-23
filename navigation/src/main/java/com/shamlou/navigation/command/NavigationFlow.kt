package com.shamlou.navigation.command

import androidx.navigation.NavDirections
import com.shamlou.navigation.command.NavigationFlow.*
import com.shamlou.navigation.model.NavModelMap

sealed class NavigationFlow {
    data class ToMap(val navModelMap: NavModelMap) : NavigationFlow()
}


//fun NavigationFlow.toNavDirections(): NavDirections =
//    when (this) {
//        is ToMap -> //FragmentSearchDirections.
//    }