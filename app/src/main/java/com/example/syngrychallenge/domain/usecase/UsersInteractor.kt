package com.example.syngrychallenge.domain.usecase

import com.example.syngrychallenge.domain.model.UsersModel
import com.example.syngrychallenge.domain.repository.IRepository

class UsersInteractor(private val iRepository: IRepository): UsersUseCase {
    override fun createAccount(usersModel: UsersModel) =
        iRepository.createAccount(usersModel)

    override suspend fun isAccountExist(usersModel: UsersModel): String =
        iRepository.isAccountExist(usersModel)
}