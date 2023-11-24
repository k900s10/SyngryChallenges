package com.example.syngrychallenge.presentation.profile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.PhotoProfileResult
import com.example.core.data.local.pref.result.DataStoreResult
import com.example.core.domain.model.ProfileModel
import com.example.core.domain.usecase.UsersUseCase

class ProfileViewModel(private val useCase: UsersUseCase) : ViewModel() {

    fun logout(): LiveData<DataStoreResult> = useCase.logout().asLiveData()

    fun getProfile(): LiveData<ProfileModel> = useCase.getProfile().asLiveData()

    fun updateProfile(
        username: String,
        name: String,
        birthday: String,
        address: String,
        photoProfilePath: String,
    ): LiveData<DataStoreResult> {
        val profileModel = ProfileModel(
            username = username,
            name = name,
            birthday = birthday,
            address = address,
            photoProfilePath = photoProfilePath
        )
        return useCase.updateProfile(profileModel).asLiveData()
    }

    fun imageToBitmap(uri: Uri): LiveData<PhotoProfileResult<String>> = useCase.imageToBitmap(uri).asLiveData()
}