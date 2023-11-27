package com.example.core.domain.usecase

import com.example.core.data.local.pref.result.DataStoreResult
import com.example.core.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class LogoutUseCase(private val localRepository: LocalRepository) {
    fun logout(): Flow<DataStoreResult> = localRepository.logout()

}