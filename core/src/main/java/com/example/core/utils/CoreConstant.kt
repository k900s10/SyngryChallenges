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
    const val AUTHORIZATION_HEADER_VALUE = "PASTE YOUR OWN API KEY HERE!"
    const val AUTHORIZATION_HEADER_VALUE_HEADER = "Bearer "
}