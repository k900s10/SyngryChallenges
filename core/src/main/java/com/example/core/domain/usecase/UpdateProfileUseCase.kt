package com.example.core.domain.usecase

import com.example.core.data.local.pref.result.DataStoreResult
import com.example.core.domain.model.ProfileModel
import com.example.core.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class UpdateProfileUseCase(private val localRepository: LocalRepository) {
    fun updateProfile(profileModel: ProfileModel): Flow<DataStoreResult> =
        localRepository.updateProfile(profileModel)
}