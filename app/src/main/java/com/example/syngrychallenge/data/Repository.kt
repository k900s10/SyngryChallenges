package com.example.syngrychallenge.data

import com.example.syngrychallenge.data.local.LocalDataStore
import com.example.syngrychallenge.data.remote.RemoteDataStore
import com.example.syngrychallenge.data.remote.response.ApiResponse
import com.example.syngrychallenge.domain.model.CastsModel
import com.example.syngrychallenge.domain.model.LoginModel
import com.example.syngrychallenge.domain.model.NewMoviesModel
import com.example.syngrychallenge.domain.model.ProfileModel
import com.example.syngrychallenge.domain.model.RegisterModel
import com.example.syngrychallenge.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class Repository(
    private val localDataStore: LocalDataStore,
    private val remoteDataStore: RemoteDataStore
) : IRepository {

    override fun isLogin(): Flow<Boolean> =
        localDataStore.isLogin()

    override fun getProfile(): Flow<ProfileModel> = localDataStore.getProfile()

    override suspend fun auth(input: LoginModel): Boolean {
        val preference = localDataStore.auth()

        return input.email == preference.email && input.password == preference.password
    }

    override fun createAccount(registerModel: RegisterModel) =
        localDataStore.createAccount(registerModel)

    override fun createLoginSession() = localDataStore.createLoginSession()

    override fun logout() = localDataStore.logout()

    override fun updateProfile(profileModel: ProfileModel) =
        localDataStore.updateProfile(profileModel)

    override fun getNewMovie(): Flow<ApiResponse<List<NewMoviesModel>>> =
        remoteDataStore.getNewMovies()

    override fun getPopularMovie(): Flow<ApiResponse<List<NewMoviesModel>>> =
        remoteDataStore.getPopularMovies()

    override fun getMovieCasts(movieId: Int): Flow<ApiResponse<List<CastsModel>>> =
        remoteDataStore.getMovieCredits(movieId)
}