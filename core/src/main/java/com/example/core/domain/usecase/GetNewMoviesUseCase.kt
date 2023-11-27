package com.example.core.domain.usecase

import android.util.Log
import com.example.core.data.remote.response.ApiResponse
import com.example.core.domain.model.NewMoviesModel
import com.example.core.domain.repository.RemoteRepository
import com.example.core.utils.CoreConstant
import com.example.core.utils.DataMapper
import com.example.core.utils.result.GetMoviesResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetNewMoviesUseCase(private val remoteRepository: RemoteRepository) {
    fun getNewMovies(): Flow<GetMoviesResult<List<NewMoviesModel>>> = flow {
        try {
            when (val rawData = remoteRepository.getNewMovies()) {
                is ApiResponse.Success -> {
                    val data = DataMapper.mapNewMovieResponseToDomain(rawData.data)
                    emit(GetMoviesResult.Success(data))
                }

                is ApiResponse.Error ->
                    throw Exception(CoreConstant.FAILED_TO_GET_API_RESPONSE)

                is ApiResponse.Empty -> {
                    delay(3000)
                    getNewMovies()
                    throw Exception(CoreConstant.EMPTY_DATA)
                }
            }
        } catch (e: Exception) {
            Log.e(CoreConstant.GET_NEW_MOVIE_USE_CASE_TAG, "getNewMovies: ", e)
            emit(GetMoviesResult.Failed)
        }
    }.flowOn(Dispatchers.IO)
}