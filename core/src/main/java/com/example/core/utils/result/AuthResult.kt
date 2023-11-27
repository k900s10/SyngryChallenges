package com.example.core.utils.result

sealed class AuthResult {
    data object Success : AuthResult()

    data object Failed : AuthResult()
}
