package com.example.core.utils

object CoreConstant {
    //preference
    const val USER_PREF = "user_pref"

    //movie api url
    val MOVIE_POSTER_URL = "https://image.tmdb.org/t/p/original/"
    val MOVIES_URL = "https://api.themoviedb.org/3/"

    //movie api
    const val ACCEPT_HEADER_LABEL = "accept"
    const val AUTHORIZATION_HEADER_LABEL = "Authorization"
    const val ACCEPT_HEADER_VALUE = "application/json"
    //Please paste your themoviedb.org API key into variable AUTHORIZATION_HEADER_VALUE below before running the app.
    const val AUTHORIZATION_HEADER_VALUE = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmMTI5ZGRlMmVkNDc0MzI4YTJmYzVhM2QxNzNiZGQ2MiIsInN1YiI6IjY1NDFmYzRiMWFjMjkyMDBhYmQ2ZDE3YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.bw3IcRKM_bj8kHz5qmPHSlqXGX8W03sO-FrJkSx0xQ4"
    const val AUTHORIZATION_HEADER_VALUE_HEADER = "Bearer "

    //worker
    @JvmField val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence =
        "Verbose WorkManager Notifications"
    const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
        "Shows notifications whenever work starts"
    @JvmField val NOTIFICATION_TITLE: CharSequence = "Photo Profile"
    const val CHANNEL_ID = "VERBOSE_NOTIFICATION"
    const val NOTIFICATION_ID = 1
        // The name of the image manipulation work
    const val KEY_IMAGE_URI = "KEY_IMAGE_URI"
}