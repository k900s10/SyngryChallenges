package com.example.core.data

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import java.io.IOException

class Repository(
    private val localDataStore: LocalDataStore,
    private val remoteDataStore: RemoteDataStore,
    private val context: Context
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

    override fun updateProfile(profileModel: ProfileModel): Flow<DataStoreResult> = localDataStore.updateProfile(profileModel)

    override fun getNewMovie(): Flow<ApiResponse<List<NewMoviesModel>>> =
        remoteDataStore.getNewMovies()

    override fun getPopularMovie(): Flow<ApiResponse<List<NewMoviesModel>>> =
        remoteDataStore.getPopularMovies()

    override fun getMovieCasts(movieId: Int): Flow<ApiResponse<List<CastsModel>>> =
        remoteDataStore.getMovieCredits(movieId)

    @SuppressLint("NewApi")
    override fun imageToBitmap(uri: Uri): Flow<PhotoProfileResult<String>> = flow {
        val imageBitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(
                    context.contentResolver,
                    uri
                )
            )
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }

        emit(saveImageToLocalStorage(imageBitmap))
    }.flowOn(Dispatchers.IO)


    @RequiresApi(Build.VERSION_CODES.FROYO)
    private fun saveImageToLocalStorage(image: Bitmap): PhotoProfileResult<String> {
        return try {
            val destinationPath =
                File(context.getExternalFilesDir("image"), "photoProfile.jpg")
            val fileOutputStream = destinationPath.outputStream() //destination
            image.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            fileOutputStream.close()

            if (destinationPath.path.isNullOrEmpty())
                throw IllegalArgumentException("you are not suppose to see this")

            PhotoProfileResult.Success(destinationPath.path)
        } catch (e: Exception) {
            Log.e("saveImage", "saveImage: ", e)
            PhotoProfileResult.Error
        } catch (e: IOException) {
            Log.e("saveImage", "saveImage: ", e)
            PhotoProfileResult.Error
        } catch (e: IllegalArgumentException) {
            Log.e("saveImage", "saveImage: ", e)
            PhotoProfileResult.Error
        }

    }

}