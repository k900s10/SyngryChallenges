package com.example.syngrychallenge.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syngrychallenge.domain.model.UsersModel
import com.example.syngrychallenge.domain.usecase.UsersUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val useCase: UsersUseCase) : ViewModel() {
    private val _validation = MutableLiveData<String>()
    val validation: LiveData<String> = _validation

    fun isAccountExist(email: String, password: String) {
        val usersModel = UsersModel(
            email = email,
            password = password,
            username = "null"
        )
        viewModelScope.launch {
            val data = useCase.isAccountExist(usersModel)
            _validation.value = data
        }
    }
}