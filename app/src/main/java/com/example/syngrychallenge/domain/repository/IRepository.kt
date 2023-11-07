package com.example.syngrychallenge.domain.repository
import com.example.syngrychallenge.data.remote.response.ApiResponse
import com.example.syngrychallenge.domain.model.CastsModel
import com.example.syngrychallenge.domain.model.LoginModel
import com.example.syngrychallenge.domain.model.NewMoviesModel
import com.example.syngrychallenge.domain.model.RegisterModel
import com.example.syngrychallenge.domain.model.ProfileModel
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun isLogin(): Boolean

    fun getProfile(): Flow<ProfileModel>

    fun createAccount(registerModel: RegisterModel)

    fun createLoginSession()

    fun logout()

    fun updateProfile(profileModel: ProfileModel)
    fun auth(input: LoginModel): Boolean

    fun getNewMovie(): Flow<ApiResponse<List<NewMoviesModel>>>
    fun getPopularMovie(): Flow<ApiResponse<List<NewMoviesModel>>>
    fun getMovieCasts(movieId: Int): Flow<ApiResponse<List<CastsModel>>>
}