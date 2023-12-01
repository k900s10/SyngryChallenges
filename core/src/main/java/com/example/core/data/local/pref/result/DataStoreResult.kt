package com.example.core.data.local.pref.result

sealed class DataStoreResult {
    data object Success : DataStoreResult()
    data object Error : DataStoreResult()
}