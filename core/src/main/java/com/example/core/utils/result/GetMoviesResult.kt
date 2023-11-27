package com.example.core.utils.result

import com.example.core.domain.model.NewMoviesModel

sealed class GetMoviesResult<out R> {
    data class Success<T>(val data: List<NewMoviesModel>) : GetMoviesResult<T>()

    data object Failed : GetMoviesResult<Nothing>()
}
