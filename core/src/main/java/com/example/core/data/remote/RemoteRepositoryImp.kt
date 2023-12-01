package com.example.core.data.remote

import android.util.Log
import com.example.core.data.remote.response.ApiResponse
import com.example.core.data.remote.response.CastItem
import com.example.core.data.remote.response.ResultsItem
import com.example.core.data.remote.services.MoviesService
import com.example.core.domain.repository.RemoteRepository
import com.example.core.utils.CoreConstant

class RemoteRepositoryImp(private val service: MoviesService) : RemoteRepository {

    override suspend fun getNewMovies(): ApiResponse<List<ResultsItem>> =
        try {
            val response = service.getNewMovies()
            val data = response.results

            if (data.isNotEmpty()) {
                ApiResponse.Success(data)
            } else {
                throw Exception(CoreConstant.EMPTY_DATA)
            }
        } catch (e: Exception) {
            Log.e(CoreConstant.REMOTE_REPOSITORY_TAG, "getNewMovies: ", e)
            ApiResponse.Empty
        } catch (e: IllegalArgumentException) {
            Log.e(CoreConstant.REMOTE_REPOSITORY_TAG, "getNewMovies: ", e)
            ApiResponse.Error(e.toString())
        }

    override suspend fun getPopularMovies(): ApiResponse<List<ResultsItem>> =
        try {
            val response = service.getPopularMovies()
            val data = response.results

            if (data.isNotEmpty()) {
                ApiResponse.Success(data)
            } else {
                throw Exception(CoreConstant.EMPTY_DATA)
            }
        } catch (e: Exception) {
            Log.e(CoreConstant.REMOTE_REPOSITORY_TAG, "getPopularMovies: ", e)
            ApiResponse.Empty
        } catch (e: IllegalArgumentException) {
            Log.e(CoreConstant.REMOTE_REPOSITORY_TAG, "getPopularMovies: ", e)
            ApiResponse.Error(e.toString())
        }

    override suspend fun getMovieCredits(movieId: Int): ApiResponse<List<CastItem>> =
        try {
            val response = service.getMovieCredits(movieId = movieId.toString())
            val data = response.cast

            if (data.isNotEmpty()) {
                ApiResponse.Success(data)
            } else {
                throw Exception(CoreConstant.EMPTY_DATA)
            }
        } catch (e: Exception) {
            Log.e(CoreConstant.REMOTE_REPOSITORY_TAG, "getPopularMovies: ", e)
            ApiResponse.Empty
        } catch (e: IllegalArgumentException) {
            Log.e(CoreConstant.REMOTE_REPOSITORY_TAG, "getPopularMovies: ", e)
            ApiResponse.Error(e.toString())
        }
}