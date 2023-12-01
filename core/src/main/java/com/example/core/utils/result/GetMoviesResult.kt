package com.example.core.utils.result

import com.example.core.domain.model.MovieModel

sealed class GetMoviesResult<out R> {
    data class Success<T>(val data: List<MovieModel>) : GetMoviesResult<T>()

    data object Failed : GetMoviesResult<Nothing>()
}
