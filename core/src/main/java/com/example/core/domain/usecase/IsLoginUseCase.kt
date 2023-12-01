package com.example.core.domain.usecase

import com.example.core.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class IsLoginUseCase(private val localRepository: LocalRepository) {
    fun isLogin(): Flow<Boolean> = localRepository.isLogin()

}