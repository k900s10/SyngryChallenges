package com.example.syngrychallenge.presentation.profile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.local.pref.result.DataStoreResult
import com.example.core.domain.model.ProfileModel
import com.example.core.domain.usecase.GetProfileUseCase
import com.example.core.domain.usecase.LogoutUseCase
import com.example.core.domain.usecase.UpdatePhotoProfileUseCase
import com.example.core.domain.usecase.UpdateProfileUseCase

class ProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val updatePhotoProfileUseCase: UpdatePhotoProfileUseCase
) : ViewModel() {

    fun logout(): LiveData<DataStoreResult> = logoutUseCase.logout().asLiveData()

    fun getProfile(): LiveData<ProfileModel> = getProfileUseCase.getProfile().asLiveData()

    fun updateProfile(
        username: String,
        name: String,
        birthday: String,
        address: String,
    ): LiveData<DataStoreResult> {
        val profileModel = ProfileModel(
            username = username,
            name = name,
            birthday = birthday,
            address = address,
        )
        return updateProfileUseCase.updateProfile(profileModel).asLiveData()
    }

    fun blurImage(uri: Uri) = updatePhotoProfileUseCase.blurImageUsingWorker(uri)
}