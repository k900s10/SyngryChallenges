package com.example.syngrychallenge.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.LoginModel
import com.example.core.domain.usecase.UsersUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val useCase: UsersUseCase) : ViewModel() {
    private val _validation = MutableLiveData<Boolean>()
    val validation: LiveData<Boolean> = _validation
    val isLogin = useCase.isLogin().asLiveData()

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

    val createLoginSession = useCase.createLoginSession().asLiveData()
}