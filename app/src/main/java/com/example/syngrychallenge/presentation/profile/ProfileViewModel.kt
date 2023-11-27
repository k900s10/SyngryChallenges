package com.example.syngrychallenge.presentation.profile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
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
    ): LiveData<DataStoreResult> {
        val profileModel = ProfileModel(
            username = username,
            name = name,
            birthday = birthday,
            address = address,
        )
        return useCase.updateProfile(profileModel).asLiveData()
    }

    fun saveImage(uri: Uri) = useCase.saveImage(uri)
}