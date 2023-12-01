package com.example.syngrychallenge.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import com.example.core.domain.model.LoginModel
import com.example.core.domain.usecase.AuthUseCase
import com.example.core.domain.usecase.IsLoginUseCase
import com.example.core.utils.result.AuthResult

class LoginViewModel(
    private val authUseCase: AuthUseCase,
    isLoginUseCase: IsLoginUseCase,
) : ViewModel() {

    val isLogin = isLoginUseCase.isLogin().asLiveData()

    fun auth(email: String, password: String): LiveData<AuthResult> =
        if (email != "" && password != "") {
            val loginModel = LoginModel(
                email = email,
                password = password,
            )
            authUseCase.auth(loginModel).asLiveData()
        } else {
            MutableLiveData(AuthResult.Failed).asFlow().asLiveData()
        }
}