package com.example.syngrychallenge

import android.app.Application
import com.example.syngrychallenge.di.notesDatabaseModule
import com.example.syngrychallenge.di.repositoryModule
import com.example.syngrychallenge.di.noteUseCaseModule
import com.example.syngrychallenge.di.usersDatabaseModule
import com.example.syngrychallenge.di.usersUseCaseModule
import com.example.syngrychallenge.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    notesDatabaseModule,
                    repositoryModule,
                    noteUseCaseModule,
                    viewModelModule,
                    usersDatabaseModule,
                    usersUseCaseModule
                )
            )
        }
    }
}