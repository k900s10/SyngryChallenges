package com.example.core.domain.usecase

import com.example.core.domain.model.ProfileModel
import com.example.core.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class GetProfileUseCase(private val localRepository: LocalRepository) {
    fun getProfile(): Flow<ProfileModel> = localRepository.getProfile()

}