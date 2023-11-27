package com.example.syngrychallenge.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.local.pref.result.DataStoreResult
import com.example.core.domain.model.RegisterModel
import com.example.core.domain.usecase.CreateAccountUseCase
import kotlinx.coroutines.flow.flow

class RegisterViewModel(
    private val createAccountUseCase: CreateAccountUseCase
) : ViewModel() {

    fun createAccount(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): LiveData<DataStoreResult> {
        if (password == confirmPassword) {
            val registerModel = RegisterModel(
                username = username,
                email = email,
                password = password
            )
            return createAccountUseCase.createAccount(registerModel).asLiveData()
        }
        return flow<DataStoreResult> { DataStoreResult.Error }.asLiveData()
    }
}