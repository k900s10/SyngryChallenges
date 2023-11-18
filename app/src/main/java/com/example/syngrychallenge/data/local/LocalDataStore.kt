package com.example.syngrychallenge.data.local

import com.example.syngrychallenge.data.local.pref.UserDataStore
import com.example.syngrychallenge.data.local.pref.result.DataStoreResult
import com.example.syngrychallenge.domain.model.LoginModel
import com.example.syngrychallenge.domain.model.RegisterModel
import com.example.syngrychallenge.domain.model.ProfileModel
import kotlinx.coroutines.flow.Flow

class LocalDataStore(private val userPreference: UserDataStore) {
    fun isLogin() : Flow<Boolean> = userPreference.isLogin

    fun getProfile() : Flow<ProfileModel> = userPreference.getProfile()

    suspend fun auth(): LoginModel = userPreference.auth()

    fun createAccount(registerModel: RegisterModel): Flow<DataStoreResult> = userPreference.createAccount(registerModel)
     fun createLoginSession(): Flow<DataStoreResult> = userPreference.createLoginSession()

     fun logout(): Flow<DataStoreResult> = userPreference.logout()

     fun updateProfile(profileModel: ProfileModel): Flow<DataStoreResult> = userPreference.updateProfile(profileModel)
}