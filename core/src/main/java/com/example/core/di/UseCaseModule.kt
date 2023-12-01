package com.example.core.di

import androidx.work.WorkManager
import com.example.core.domain.usecase.AuthUseCase
import com.example.core.domain.usecase.CreateAccountUseCase
import com.example.core.domain.usecase.GetMovieCastsUseCase
import com.example.core.domain.usecase.GetNewMoviesUseCase
import com.example.core.domain.usecase.GetPopularMoviesUseCase
import com.example.core.domain.usecase.GetProfileUseCase
import com.example.core.domain.usecase.IsLoginUseCase
import com.example.core.domain.usecase.LogoutUseCase
import com.example.core.domain.usecase.UpdatePhotoProfileUseCase
import com.example.core.domain.usecase.UpdateProfileUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val useCaseModule2 = module {
    single { AuthUseCase(get()) }
    single { CreateAccountUseCase(get()) }
    single { GetMovieCastsUseCase(get()) }
    single { GetNewMoviesUseCase(get()) }
    single { GetPopularMoviesUseCase(get()) }
    single { GetProfileUseCase(get()) }
    single { IsLoginUseCase(get()) }
    single { LogoutUseCase(get()) }
    single { UpdatePhotoProfileUseCase(WorkManager.getInstance(androidApplication()), get()) }
    single { UpdateProfileUseCase(get()) }
}
