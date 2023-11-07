package com.example.syngrychallenge.data.remote.response

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class NewMoviesResponse(

	@Json(name="page")
	val page: Int,

	@Json(name="total_pages")
	val totalPages: Int,

	@Json(name="results")
	val results: List<ResultsItem>,

	@Json(name="total_results")
	val totalResults: Int
)

@JsonClass(generateAdapter = true)
data class ResultsItem(

	@Json(name="overview")
	val overview: String,

	@Json(name="title")
	val title: String,

	@Json(name="poster_path")
	val posterPath: String,

	@Json(name="backdrop_path")
	val backdropPath: String,

	@Json(name="release_date")
	val releaseDate: String,

	@Json(name="popularity")
	val popularity: String,

	@Json(name="vote_average")
	val voteAverage: String,

	@Json(name="id")
	val id: Int,
)
