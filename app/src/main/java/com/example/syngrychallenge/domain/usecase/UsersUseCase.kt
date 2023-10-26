package com.example.syngrychallenge.domain.usecase

import com.example.syngrychallenge.domain.model.UsersModel

interface UsersUseCase {
    fun createAccount(usersModel: UsersModel)

    suspend fun isAccountExist(usersModel: UsersModel): String

}