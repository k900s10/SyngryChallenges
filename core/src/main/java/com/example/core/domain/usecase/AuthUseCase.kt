package com.example.core.domain.usecase

import android.util.Log
import com.example.core.data.local.pref.result.DataStoreResult
import com.example.core.domain.model.LoginModel
import com.example.core.domain.repository.LocalRepository
import com.example.core.utils.CoreConstant
import com.example.core.utils.result.AuthResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthUseCase(private val localRepository: LocalRepository) {
    fun auth(input: LoginModel): Flow<AuthResult> = flow {
        try {
            val preference = localRepository.auth()

            val auth = input.email == preference.email && input.password == preference.password

            if (auth) {

                when (localRepository.createLoginSession()) {
                    DataStoreResult.Success -> {
                        emit(AuthResult.Success)
                    }

                    DataStoreResult.Error -> {
                        throw Exception(CoreConstant.CREATE_SESSION_FAILED)
                    }
                }
            } else {
                throw Exception(CoreConstant.WRONG_EMAIL_PASSWORD)
            }
        } catch (e: Exception) {
            Log.e(CoreConstant.AUTH_USE_CASE_TAG, "auth: ", e)
            emit(AuthResult.Failed)
        }
    }.flowOn(Dispatchers.IO)

}