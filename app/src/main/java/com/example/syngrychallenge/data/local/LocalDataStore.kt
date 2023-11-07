package com.example.syngrychallenge.data.local

import com.example.syngrychallenge.data.local.pref.UserPreference
import com.example.syngrychallenge.domain.model.LoginModel
import com.example.syngrychallenge.domain.model.RegisterModel
import com.example.syngrychallenge.domain.model.ProfileModel
import kotlinx.coroutines.flow.Flow

class LocalDataStore(private val userPreference: UserPreference) {
    fun isLogin() : Boolean = userPreference.isLogin

    fun getProfile() : Flow<ProfileModel> = userPreference.getProfile()

    fun auth(): LoginModel = userPreference.auth()

    fun createAccount(registerModel: RegisterModel) = userPreference.createAccount(registerModel)
    fun createLoginSession() =
        userPreference.createLoginSession()

    fun logout() =
        userPreference.logout()

    fun updateProfile(profileModel: ProfileModel) = userPreference.updateProfile(profileModel)
}