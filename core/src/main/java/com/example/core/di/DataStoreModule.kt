package com.example.core.di

import com.example.core.data.local.pref.UserDataStore
import com.example.core.data.local.pref.userDataStore
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val userDataStoreModule = module {
    single { UserDataStore(androidApplication().userDataStore) }
}
