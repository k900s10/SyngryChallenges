package com.example.core.domain.usecase

import android.net.Uri
import com.example.core.data.local.pref.result.DataStoreResult
import com.example.core.data.remote.response.ApiResponse
import com.example.core.domain.model.CastsModel
import com.example.core.domain.model.LoginModel
import com.example.core.domain.model.NewMoviesModel
import com.example.core.domain.model.ProfileModel
import com.example.core.domain.model.RegisterModel
import com.example.core.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class UsersInteractor(private val iRepository: IRepository) : UsersUseCase {
    override fun isLogin(): Flow<Boolean> = iRepository.isLogin()

    override fun getProfile(): Flow<ProfileModel> = iRepository.getProfile()

    override suspend fun auth(input: LoginModel): Boolean = iRepository.auth(input)

    override fun createAccount(registerModel: RegisterModel) =
        iRepository.createAccount(registerModel)

    override fun createLoginSession(): Flow<DataStoreResult> = iRepository.createLoginSession()

    override fun logout(): Flow<DataStoreResult> = iRepository.logout()

    override fun updateProfile(profileModel: ProfileModel): Flow<DataStoreResult> =
        iRepository.updateProfile(profileModel)

    override fun getNewMovie(): Flow<ApiResponse<List<NewMoviesModel>>> = iRepository.getNewMovie()

    override fun getPopularMovie(): Flow<ApiResponse<List<NewMoviesModel>>> =
        iRepository.getPopularMovie()

    override fun getMovieCasts(movieId: Int): Flow<ApiResponse<List<CastsModel>>> =
        iRepository.getMovieCasts(movieId)

    override fun saveImage(uri: Uri) = iRepository.saveImage(uri)
}