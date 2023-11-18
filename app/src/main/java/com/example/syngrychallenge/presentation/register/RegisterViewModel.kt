package com.example.syngrychallenge.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.syngrychallenge.data.local.pref.result.DataStoreResult
import com.example.syngrychallenge.domain.model.RegisterModel
import com.example.syngrychallenge.domain.usecase.UsersUseCase
import kotlinx.coroutines.flow.flow

class RegisterViewModel(private val useCase: UsersUseCase) : ViewModel() {

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
            return useCase.createAccount(registerModel).asLiveData()
        }
        return flow<DataStoreResult> { DataStoreResult.Error }.asLiveData()
    }
}