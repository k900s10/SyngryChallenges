package com.example.core.domain.repository
import android.net.Uri
import com.example.core.data.PhotoProfileResult
import com.example.core.data.local.pref.result.DataStoreResult
import com.example.core.data.remote.response.ApiResponse
import com.example.core.domain.model.CastsModel
import com.example.core.domain.model.LoginModel
import com.example.core.domain.model.NewMoviesModel
import com.example.core.domain.model.RegisterModel
import com.example.core.domain.model.ProfileModel
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun isLogin(): Flow<Boolean>

    fun getProfile(): Flow<ProfileModel>

    fun createAccount(registerModel: RegisterModel): Flow<DataStoreResult>

    fun createLoginSession(): Flow<DataStoreResult>

    fun logout(): Flow<DataStoreResult>

    fun updateProfile(profileModel: ProfileModel): Flow<DataStoreResult>
    suspend fun auth(input: LoginModel): Boolean

    fun getNewMovie(): Flow<ApiResponse<List<NewMoviesModel>>>
    fun getPopularMovie(): Flow<ApiResponse<List<NewMoviesModel>>>
    fun getMovieCasts(movieId: Int): Flow<ApiResponse<List<CastsModel>>>

    fun imageToBitmap(uri: Uri): Flow<PhotoProfileResult<String>>
}