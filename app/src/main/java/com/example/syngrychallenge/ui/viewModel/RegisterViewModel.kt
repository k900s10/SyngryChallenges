package com.example.syngrychallenge.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.syngrychallenge.domain.model.RegisterModel
import com.example.syngrychallenge.domain.usecase.UsersUseCase

class RegisterViewModel(private val useCase: UsersUseCase) : ViewModel() {

    fun createAccount(username: String, email: String, password: String, confirmPassword: String) {
        if (password == confirmPassword) {
            val registerModel = RegisterModel(
                username = username,
                email = email,
                password = password
            )
            useCase.createAccount(registerModel)
        }
    }
}