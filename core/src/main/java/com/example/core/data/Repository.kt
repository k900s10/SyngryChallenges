package com.example.core.data

import android.content.Context
import android.net.Uri
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.core.BlurImageWorker
import com.example.core.data.local.LocalDataStore
import com.example.core.data.local.pref.result.DataStoreResult
import com.example.core.data.remote.RemoteDataStore
import com.example.core.data.remote.response.ApiResponse
import com.example.core.domain.model.CastsModel
import com.example.core.domain.model.LoginModel
import com.example.core.domain.model.NewMoviesModel
import com.example.core.domain.model.ProfileModel
import com.example.core.domain.model.RegisterModel
import com.example.core.domain.repository.IRepository
import com.example.core.utils.CoreConstant
import kotlinx.coroutines.flow.Flow

class Repository(
    private val localDataStore: LocalDataStore,
    private val remoteDataStore: RemoteDataStore,
    context: Context
) : IRepository {
    private val workManager = WorkManager.getInstance(context)

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

    override fun updateProfile(profileModel: ProfileModel): Flow<DataStoreResult> = localDataStore.updateProfile(profileModel)

    override fun getNewMovie(): Flow<ApiResponse<List<NewMoviesModel>>> =
        remoteDataStore.getNewMovies()

    override fun getPopularMovie(): Flow<ApiResponse<List<NewMoviesModel>>> =
        remoteDataStore.getPopularMovies()

    override fun getMovieCasts(movieId: Int): Flow<ApiResponse<List<CastsModel>>> =
        remoteDataStore.getMovieCredits(movieId)

    override suspend fun setProfilePicture(input: String): DataStoreResult = localDataStore.setProfilePicture(input)

    override fun saveImage(uri: Uri) {
        val blurBuilder = OneTimeWorkRequestBuilder<BlurImageWorker>()

        blurBuilder.setInputData(createInputDataForWorkRequest(uri.toString()))
        workManager.enqueue(blurBuilder.build())
    }

    private fun createInputDataForWorkRequest(uri: String): Data {
        val builder = Data.Builder()
        builder.putString(CoreConstant.KEY_IMAGE_URI, uri)
        return builder.build()
    }

}