package com.shamlou.navigation.ktx

import androidx.navigation.NavController
import java.lang.Exception

/**
 * used to prevent crashes that may accrue during navigation
 */
inline fun NavController.doSafely(block: NavController.() -> Unit) =
    try {
        block()
    } catch (e: Exception) {
        // Add Logger here to catch failed navigation
        e.printStackTrace()
    }