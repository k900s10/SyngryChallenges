package com.example.syngrychallenge.utils

import androidx.navigation.NavController
import androidx.navigation.NavDirections

object Util {
    fun NavController.safeNavigate(direction: NavDirections) {
        currentDestination?.getAction(direction.actionId)?.run {
            navigate(direction)
        }
    }

}