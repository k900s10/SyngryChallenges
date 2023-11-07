package com.example.syngrychallenge.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewMoviesModel(
    val overview: String,

    val title: String,

    val posterPath: String,

    val backdropPath: String,

    val releaseDate: String,

    val popularity: String,

    val voteAverage: String,

    val id: Int,
): Parcelable
