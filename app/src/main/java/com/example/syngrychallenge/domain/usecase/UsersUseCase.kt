package com.example.syngrychallenge.domain.usecase

import com.example.syngrychallenge.data.local.pref.result.DataStoreResult
import com.example.syngrychallenge.data.remote.response.ApiResponse
import com.example.syngrychallenge.domain.model.CastsModel
import com.example.syngrychallenge.domain.model.LoginModel
import com.example.syngrychallenge.domain.model.NewMoviesModel
import com.example.syngrychallenge.domain.model.RegisterModel
import com.example.syngrychallenge.domain.model.ProfileModel
import kotlinx.coroutines.flow.Flow

interface UsersUseCase {
    fun isLogin(): Flow<Boolean>

    fun getProfile(): Flow<ProfileModel>

    suspend fun auth(input: LoginModel): Boolean

    fun createAccount(registerModel: RegisterModel): Flow<DataStoreResult>
    fun createLoginSession(): Flow<DataStoreResult>

    fun logout(): Flow<DataStoreResult>

    fun updateProfile(profileModel: ProfileModel): Flow<DataStoreResult>

    fun getNewMovie(): Flow<ApiResponse<List<NewMoviesModel>>>

    fun getPopularMovie(): Flow<ApiResponse<List<NewMoviesModel>>>

    fun getMovieCasts(movieId: Int): Flow<ApiResponse<List<CastsModel>>>
}