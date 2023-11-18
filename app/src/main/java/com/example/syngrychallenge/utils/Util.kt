package com.example.syngrychallenge.utils

import androidx.navigation.NavController
import androidx.navigation.NavDirections

object Util {
    //Presentation
    fun NavController.safeNavigate(direction: NavDirections) {
        currentDestination?.getAction(direction.actionId)?.run {
            navigate(direction)
        }
    }


    //for DataMapper
    fun String.trimScore(): String =
        if (this.length > 3)
            this.take(3)
        else
            this

    fun String.worldCalendarFormat() =
        this.split("-").reversed().joinToString("/")
}