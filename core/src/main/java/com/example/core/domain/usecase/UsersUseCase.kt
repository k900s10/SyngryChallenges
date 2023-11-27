package com.example.core.domain.usecase

import android.net.Uri
import com.example.core.data.local.pref.result.DataStoreResult
import com.example.core.data.remote.response.ApiResponse
import com.example.core.domain.model.CastsModel
import com.example.core.domain.model.LoginModel
import com.example.core.domain.model.NewMoviesModel
import com.example.core.domain.model.ProfileModel
import com.example.core.domain.model.RegisterModel
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

    fun saveImage(uri: Uri)
}