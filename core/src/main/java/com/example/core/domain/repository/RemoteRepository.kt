package com.example.core.domain.repository

import com.example.core.data.remote.response.ApiResponse
import com.example.core.data.remote.response.CastItem
import com.example.core.data.remote.response.ResultsItem

interface RemoteRepository {
    suspend fun getPopularMovies(): ApiResponse<List<ResultsItem>>

    suspend fun getMovieCredits(movieId: Int): ApiResponse<List<CastItem>>

    suspend fun getNewMovies(): ApiResponse<List<ResultsItem>>
}