package com.example.syngrychallenge.data.remote

import android.content.Context
import android.util.Log
import com.example.syngrychallenge.R
import com.example.syngrychallenge.data.remote.response.ApiResponse
import com.example.syngrychallenge.data.remote.services.MoviesService
import com.example.syngrychallenge.domain.model.ApiHeader
import com.example.syngrychallenge.domain.model.CastsModel
import com.example.syngrychallenge.domain.model.NewMoviesModel
import com.example.syngrychallenge.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataStore(private val context: Context, private val service: MoviesService) {

    fun getNewMovies(): Flow<ApiResponse<List<NewMoviesModel>>> = flow {
        try {
            val header = getHeader()
            val response = service.getNewMovies(
                auth = header.auth,
                accept = header.accept,
            )
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

    fun getPopularMovies() : Flow<ApiResponse<List<NewMoviesModel>>> = flow {
        try {
            val header = getHeader()
            val response = service.getPopularMovies(
                auth = header.auth,
                accept = header.accept
            )
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

    fun getMovieCredits(movieId: Int) : Flow<ApiResponse<List<CastsModel>>> = flow {
        try {
            val header = getHeader()
            val response = service.getMovieCredits(
                auth = header.auth,
                accept = header.accept,
                movieId = movieId.toString()
            )
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

    private fun getHeader(): ApiHeader = ApiHeader(
        auth = context.getString(R.string.Failed_attemp),
        accept = context.getString(R.string.Accept),
    )
}