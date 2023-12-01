package com.example.core.data.local

import com.example.core.data.local.pref.UserDataStore
import com.example.core.data.local.pref.result.DataStoreResult
import com.example.core.domain.model.LoginModel
import com.example.core.domain.model.ProfileModel
import com.example.core.domain.model.RegisterModel
import com.example.core.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class LocalRepositoryImp(private val userPreference: UserDataStore): LocalRepository {
    override fun isLogin(): Flow<Boolean> = userPreference.isLogin

    override fun getProfile(): Flow<ProfileModel> = userPreference.getProfile()

    override suspend fun auth(): LoginModel = userPreference.auth()

    override fun createAccount(registerModel: RegisterModel): Flow<DataStoreResult> =
        userPreference.createAccount(registerModel)

    override suspend fun createLoginSession(): DataStoreResult = userPreference.createLoginSession()

    override fun logout(): Flow<DataStoreResult> = userPreference.logout()

    override fun updateProfile(profileModel: ProfileModel): Flow<DataStoreResult> =
        userPreference.updateProfile(profileModel)

    override suspend fun saveProfilePicture(input: String): DataStoreResult = userPreference.saveProfilePicture(input)
}