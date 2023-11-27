package com.example.core.utils

object CoreConstant {
    //datastore preference message
    const val CREATE_SESSION_FAILED = "Failed creating sessing"
    const val WRONG_EMAIL_PASSWORD = "Email & password is wrong"

    //Api message
    const val EMPTY_DATA = "Data is empty"
    const val REMOTE_REPOSITORY_TAG = "remoteRepository"
    const val FAILED_TO_GET_API_RESPONSE = "Failed to get API response"

    //Tag
    const val AUTH_USE_CASE_TAG = "authUseCase"
    const val GET_NEW_MOVIE_USE_CASE_TAG = "getNewMoviesUseCase"
    const val GET_MOVIE_CASTS_USE_CASE_TAG = "getMovieCastsUseCase"
    const val GET_POPULAR_MOVIES_USE_CASE_TAG = "getPopularMovies"

    //preference
    const val USER_PREF = "user_pref"

    //movie api url
    const val MOVIE_POSTER_URL = "https://image.tmdb.org/t/p/original/"
    const val MOVIES_URL = "https://api.themoviedb.org/3/"

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