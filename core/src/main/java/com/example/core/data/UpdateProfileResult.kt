package com.example.core.data

sealed class PhotoProfileResult<out T> {
    data class Success<String>(val data: String) : PhotoProfileResult<String>()
    data object Error : PhotoProfileResult<Nothing>()
}
