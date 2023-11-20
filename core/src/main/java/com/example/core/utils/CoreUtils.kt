package com.example.core.utils


object CoreUtils {
    //for DataMapper
    fun String.trimScore(): String =
        if (this.length > 3)
            this.take(3)
        else
            this

    fun String.worldCalendarFormat() =
        this.split("-").reversed().joinToString("/")
}