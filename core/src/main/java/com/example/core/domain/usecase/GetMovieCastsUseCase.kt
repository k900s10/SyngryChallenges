package com.example.core.domain.usecase

import android.util.Log
import com.example.core.data.remote.response.ApiResponse
import com.example.core.domain.model.CastsModel
import com.example.core.domain.repository.RemoteRepository
import com.example.core.utils.CoreConstant
import com.example.core.utils.DataMapper
import com.example.core.utils.result.GetCastsResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetMovieCastsUseCase(private val remoteRepository: RemoteRepository) {
    fun getMovieCasts(movieId: Int): Flow<GetCastsResult<List<CastsModel>>> = flow {
        try {
            when (val rawData = remoteRepository.getMovieCredits(movieId)) {
                is ApiResponse.Success -> {
                    val data = DataMapper.mapMovieCreditsToDomain(rawData.data)
                    emit(GetCastsResult.Success(data))
                }

                is ApiResponse.Error ->
                    throw Exception(CoreConstant.FAILED_TO_GET_API_RESPONSE)

                is ApiResponse.Empty -> {
                    delay(3000)
                    getMovieCasts(movieId)
                    throw Exception(CoreConstant.EMPTY_DATA)
                }
            }
        } catch (e: Exception) {
            Log.e(CoreConstant.GET_MOVIE_CASTS_USE_CASE_TAG, "getMovieCasts: ", e)
            emit(GetCastsResult.Failed)
        }
    }.flowOn(Dispatchers.IO)
}