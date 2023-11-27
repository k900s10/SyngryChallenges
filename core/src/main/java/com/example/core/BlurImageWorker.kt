package com.example.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.core.data.local.pref.result.DataStoreResult
import com.example.core.domain.repository.IRepository
import com.example.core.utils.CoreConstant
import com.example.core.utils.WorkerUtils.blurBitmap
import com.example.core.utils.WorkerUtils.makeStatusNotification
import com.example.core.utils.WorkerUtils.saveImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

//i had no idea where to put worker class.
class BlurImageWorker(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params), KoinComponent {
    private val repository: IRepository by inject()

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override suspend fun doWork(): Result {
        val path = inputData.getString(CoreConstant.KEY_IMAGE_URI)


        return withContext(Dispatchers.IO) {
            return@withContext try {
                require(!path.isNullOrEmpty()) {
                    throw Exception("Image is missing")
                }
                val imageBitmap = path.pathToBitmap()
                val blurImage = blurBitmap(imageBitmap, 5)
                val blurImagePath = blurImage.saveImage(applicationContext)
                val saveToDatastore = repository.setProfilePicture(blurImagePath)

                when (saveToDatastore) {
                    is DataStoreResult.Success ->
                        makeStatusNotification(
                            applicationContext.getString(R.string.saving_image_success),
                            applicationContext
                        )

                    is DataStoreResult.Error -> {
                        makeStatusNotification(
                            applicationContext.getString(
                                R.string.saving_image_failed
                            ), applicationContext
                        )
                        throw Exception("saving image failed")
                    }
                }
                Result.success()
            } catch (e: Exception) {
                Log.e("blurImageWorker", "doWork: ", e)
                Result.failure()
            }
        }
    }

    private fun String.pathToBitmap(): Bitmap =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(
                    applicationContext.contentResolver,
                    Uri.parse(this)
                )
            )
        } else {
            MediaStore.Images.Media.getBitmap(
                applicationContext.contentResolver,
                Uri.parse(this)
            )
        }


}