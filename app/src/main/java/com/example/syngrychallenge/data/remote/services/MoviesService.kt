package com.example.syngrychallenge.data.remote.services

import com.example.syngrychallenge.data.remote.response.MovieCreditsResponse
import com.example.syngrychallenge.data.remote.response.NewMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MoviesService {
    @GET("movie/now_playing")
    suspend fun getNewMovies(
        @Header("accept") accept: String,
        @Header("Authorization") auth: String,
    ) : NewMoviesResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Header("accept") accept: String,
        @Header("Authorization") auth: String,
    ) : NewMoviesResponse

    @GET("movie/{movieId}/credits")
    suspend fun getMovieCredits(
        @Header("accept") accept: String,
        @Header("Authorization") auth: String,
        @Path("movieId") movieId: String
    ) : MovieCreditsResponse

}