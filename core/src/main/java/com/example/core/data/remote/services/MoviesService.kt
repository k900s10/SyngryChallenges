package com.example.core.data.remote.services

import com.example.core.data.remote.response.NewMoviesResponse
import com.example.core.data.remote.response.MovieCreditsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesService {
    @GET("movie/now_playing")
    suspend fun getNewMovies(
    ) : NewMoviesResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
    ) : NewMoviesResponse

    @GET("movie/{movieId}/credits")
    suspend fun getMovieCredits(
        @Path("movieId") movieId: String
    ) : MovieCreditsResponse

}