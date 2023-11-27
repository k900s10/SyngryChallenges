package com.example.core.utils

import com.example.core.data.remote.response.CastItem
import com.example.core.data.remote.response.ResultsItem
import com.example.core.domain.model.CastsModel
import com.example.core.domain.model.NewMoviesModel
import com.example.core.utils.CoreUtils.trimScore
import com.example.core.utils.CoreUtils.worldCalendarFormat


object DataMapper {
    fun mapNewMovieResponseToDomain(data: List<ResultsItem>): List<NewMoviesModel> =
        data.map { response ->
            NewMoviesModel(
                overview = response.overview ?: "",
                title = response.title ?: "",
                posterPath = response.posterPath ?: "",
                backdropPath = response.backdropPath ?: "",
                releaseDate = response.releaseDate.worldCalendarFormat() ,
                popularity = response.popularity ?: "",
                voteAverage = response.voteAverage.trimScore(),
                id = response.id ?: 0
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