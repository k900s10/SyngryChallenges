package com.example.core.data.remote.services

import com.example.core.data.remote.response.MoviesResponse
import com.example.core.data.remote.response.MovieCreditsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesService {
    @GET("movie/now_playing")
    suspend fun getNewMovies(
    ) : MoviesResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
    ) : MoviesResponse

    @GET("movie/{movieId}/credits")
    suspend fun getMovieCredits(
        @Path("movieId") movieId: String
    ) : MovieCreditsResponse

}