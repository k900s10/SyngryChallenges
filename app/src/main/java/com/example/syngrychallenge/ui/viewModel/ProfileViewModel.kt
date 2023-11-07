package com.example.syngrychallenge.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.syngrychallenge.domain.model.ProfileModel
import com.example.syngrychallenge.domain.usecase.UsersUseCase

class ProfileViewModel(private val useCase: UsersUseCase) : ViewModel() {

    fun logout() =
        useCase.logout()

    fun getProfile() : LiveData<ProfileModel> = useCase.getProfile().asLiveData()

    fun updateProfile(
        username: String,
        name: String,
        birthday: String,
        address: String
    ) {
        val profileModel = ProfileModel(
            username = username,
            name = name,
            birthday = birthday,
            address = address
        )
        useCase.updateProfile(profileModel)
    }
}