package com.shamlou.navigation.command

import androidx.navigation.NavDirections
import com.shamlou.search.ui.search.FragmentSearchDirections

sealed class NavigationFlow {
    data class ToUserDetails(val userName: String) : NavigationFlow()
}


fun NavigationFlow.toNavDirections(): NavDirections =
    when (this) {
        is NavigationFlow.ToUserDetails -> FragmentSearchDirections.actionFragmentSearchToUserDetail(userName)
    }