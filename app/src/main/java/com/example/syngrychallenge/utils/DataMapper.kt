package com.example.syngrychallenge.utils

import com.example.syngrychallenge.data.remote.response.CastItem
import com.example.syngrychallenge.data.remote.response.ResultsItem
import com.example.syngrychallenge.domain.model.CastsModel
import com.example.syngrychallenge.domain.model.NewMoviesModel


object DataMapper {
    fun mapNewMovieResponseToDomain(data: List<ResultsItem>): List<NewMoviesModel> =
        data.map { response ->
            NewMoviesModel(
                overview = response.overview,
                title = response.title,
                posterPath = response.posterPath,
                backdropPath = response.backdropPath,
                releaseDate = response.releaseDate,
                popularity = response.popularity,
                voteAverage = response.voteAverage,
                id = response.id
            )
        }

    fun mapMovieCreditsToDomain(data: List<CastItem>): List<CastsModel> =
        data.map { response ->
            CastsModel(
                character = response.character,
                originalName = response.originalName,
                profilePath = response.profilePath,
                id = response.id,
                order = response.order
            )
        }

}