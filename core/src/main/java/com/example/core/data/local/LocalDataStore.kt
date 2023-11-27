package com.example.core.data.local

import com.example.core.data.local.pref.UserDataStore
import com.example.core.data.local.pref.result.DataStoreResult
import com.example.core.domain.model.LoginModel
import com.example.core.domain.model.ProfileModel
import com.example.core.domain.model.RegisterModel
import kotlinx.coroutines.flow.Flow

class LocalDataStore(private val userPreference: UserDataStore) {
    fun isLogin(): Flow<Boolean> = userPreference.isLogin

    fun getProfile(): Flow<ProfileModel> = userPreference.getProfile()

    suspend fun auth(): LoginModel = userPreference.auth()

    fun createAccount(registerModel: RegisterModel): Flow<DataStoreResult> =
        userPreference.createAccount(registerModel)

    fun createLoginSession(): Flow<DataStoreResult> = userPreference.createLoginSession()

    fun logout(): Flow<DataStoreResult> = userPreference.logout()

    fun updateProfile(profileModel: ProfileModel): Flow<DataStoreResult> =
        userPreference.updateProfile(profileModel)

    suspend fun setProfilePicture(input: String): DataStoreResult = userPreference.setProfilePicture(input)
}