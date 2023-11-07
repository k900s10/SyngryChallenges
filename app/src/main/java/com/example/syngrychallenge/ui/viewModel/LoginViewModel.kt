package com.example.syngrychallenge.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syngrychallenge.domain.model.LoginModel
import com.example.syngrychallenge.domain.usecase.UsersUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val useCase: UsersUseCase) : ViewModel() {
    private val _validation = MutableLiveData<Boolean>()
    val validation: LiveData<Boolean> = _validation
    val isLogin = useCase.isLogin()

    fun auth(email: String, password: String) {
        if (email != "" && password != "") {
            val loginModel = LoginModel(
                email = email,
                password = password,
            )
            viewModelScope.launch {
                val data = useCase.auth(loginModel)
                _validation.value = data
            }
        } else {
            _validation.value = false
        }
    }

    fun createLoginSession() =
        useCase.createLoginSession()
}