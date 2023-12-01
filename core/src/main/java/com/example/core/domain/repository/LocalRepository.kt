package com.example.core.domain.repository

import com.example.core.data.local.pref.result.DataStoreResult
import com.example.core.domain.model.LoginModel
import com.example.core.domain.model.ProfileModel
import com.example.core.domain.model.RegisterModel
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun isLogin(): Flow<Boolean>

    fun getProfile(): Flow<ProfileModel>

    suspend fun auth(): LoginModel

    fun createAccount(registerModel: RegisterModel): Flow<DataStoreResult>

    suspend fun createLoginSession(): DataStoreResult

    fun logout(): Flow<DataStoreResult>

    fun updateProfile(profileModel: ProfileModel): Flow<DataStoreResult>

    suspend fun saveProfilePicture(input: String): DataStoreResult
}