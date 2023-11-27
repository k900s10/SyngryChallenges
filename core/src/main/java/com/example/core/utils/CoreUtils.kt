package com.example.core.utils

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.File


object CoreUtils {
    //for DataMapper
    fun String.trimScore(): String =
        if (this.length > 3)
            this.take(3)
        else
            this

    fun String.worldCalendarFormat() =
        this.split("-").reversed().joinToString("/")

    //dataStore
    @RequiresApi(Build.VERSION_CODES.FROYO)
    fun getPhotoProfilePath(context: Context): String =
        File(context.getExternalFilesDir("image"), "photoProfile.jpg").path
}