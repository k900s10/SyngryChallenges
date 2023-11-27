package com.example.core.domain.usecase

import android.net.Uri
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.core.BlurImageWorker
import com.example.core.data.local.pref.result.DataStoreResult
import com.example.core.domain.repository.LocalRepository
import com.example.core.utils.CoreConstant

class UpdatePhotoProfileUseCase(private val workManager: WorkManager, private val localRepository: LocalRepository) {

    suspend fun saveProfilePicture(input: String): DataStoreResult = localRepository.saveProfilePicture(input)

    fun blurImageUsingWorker(uri: Uri) {
        val blurBuilder = OneTimeWorkRequestBuilder<BlurImageWorker>()

        blurBuilder.setInputData(createInputDataForWorkRequest(uri.toString()))
        workManager.enqueue(blurBuilder.build())
    }

    fun createInputDataForWorkRequest(uri: String): Data {
        val builder = Data.Builder()
        builder.putString(CoreConstant.KEY_IMAGE_URI, uri)
        return builder.build()
    }

}