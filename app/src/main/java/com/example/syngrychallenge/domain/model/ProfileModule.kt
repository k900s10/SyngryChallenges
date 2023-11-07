package com.example.syngrychallenge.domain.model

import kotlinx.coroutines.flow.Flow

data class ProfileModel(
    val username: String?,
    val name: String?,
    val birthday: String?,
    val address: String?
)
