package com.example.core.domain.usecase

import com.example.core.data.local.pref.result.DataStoreResult
import com.example.core.domain.model.RegisterModel
import com.example.core.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class CreateAccountUseCase(private val localRepository: LocalRepository) {
    fun createAccount(registerModel: RegisterModel): Flow<DataStoreResult> =
        localRepository.createAccount(registerModel)
}