package com.example.core.utils


object CoreUtils {
    //for DataMapper
    fun String?.trimScore(): String =
        if (!this.isNullOrEmpty()) {
            if (this.length > 3)
                this.take(3)
            else
                this
        } else
            ""

    fun String?.worldCalendarFormat(): String =
        if (!this.isNullOrEmpty())
            this.split("-").reversed().joinToString("/")
        else
            ""

    //dataStore
//    @RequiresApi(Build.VERSION_CODES.FROYO)
//    fun getPhotoProfilePath(context: Context): String =
//        File(context.getExternalFilesDir("image"), "photoProfile.jpg").path
}