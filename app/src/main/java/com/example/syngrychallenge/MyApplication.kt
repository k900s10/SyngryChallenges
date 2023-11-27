package com.example.syngrychallenge

import android.app.Application
import com.example.core.di.localRepositoryModule
import com.example.core.di.networkModule
import com.example.core.di.remoteRepositoryModule
import com.example.core.di.useCaseModule2
import com.example.core.di.userDataStoreModule
import com.example.syngrychallenge.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    userDataStoreModule,
                    viewModelModule,
                    useCaseModule2,
                    localRepositoryModule,
                    remoteRepositoryModule
                )
            )
        }
    }
}