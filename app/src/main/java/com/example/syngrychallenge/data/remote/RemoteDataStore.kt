package com.example.syngrychallenge.data.remote

import com.example.syngrychallenge.data.remote.response.ApiResponse
import com.example.syngrychallenge.data.remote.services.MoviesService
import com.example.syngrychallenge.domain.model.CastsModel
import com.example.syngrychallenge.domain.model.NewMoviesModel
import com.example.syngrychallenge.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataStore(private val service: MoviesService) {

    fun getNewMovies(): Flow<ApiResponse<List<NewMoviesModel>>> = flow {
        try {
            val response = service.getNewMovies()
            val rawData = response.results

            if (rawData.isNotEmpty()) {
                val newMovieModel = DataMapper.mapNewMovieResponseToDomain(rawData)
                emit(ApiResponse.Success(newMovieModel))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun getPopularMovies(): Flow<ApiResponse<List<NewMoviesModel>>> = flow {
        try {
            val response = service.getPopularMovies()
            val rawData = response.results

            if (rawData.isNotEmpty()) {
                val movies = DataMapper.mapNewMovieResponseToDomain(rawData)
                emit(ApiResponse.Success(movies))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun getMovieCredits(movieId: Int): Flow<ApiResponse<List<CastsModel>>> = flow {
        try {
            val response = service.getMovieCredits(movieId = movieId.toString())
            val rawData = response.cast
            if (rawData.isNotEmpty()) {
                val casts = DataMapper.mapMovieCreditsToDomain(rawData)
                emit(ApiResponse.Success(casts))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            ApiResponse.Error(e.toString())
        }

    }.flowOn(Dispatchers.IO)
}