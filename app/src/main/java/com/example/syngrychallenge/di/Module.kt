package com.example.syngrychallenge.di

import com.example.syngrychallenge.R
import com.example.syngrychallenge.data.Repository
import com.example.syngrychallenge.data.local.LocalDataStore
import com.example.syngrychallenge.data.local.pref.UserPreference
import com.example.syngrychallenge.data.remote.RemoteDataStore
import com.example.syngrychallenge.data.remote.services.MoviesService
import com.example.syngrychallenge.domain.repository.IRepository
import com.example.syngrychallenge.domain.usecase.UsersInteractor
import com.example.syngrychallenge.domain.usecase.UsersUseCase
import com.example.syngrychallenge.ui.viewModel.HomeViewModel
import com.example.syngrychallenge.ui.viewModel.LoginViewModel
import com.example.syngrychallenge.ui.viewModel.MovieDetailViewModel
import com.example.syngrychallenge.ui.viewModel.ProfileViewModel
import com.example.syngrychallenge.ui.viewModel.RegisterViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(androidApplication().getString(R.string.api_url))
            .addConverterFactory(MoshiConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(MoviesService::class.java)
    }
}

val userPreferenceModule = module {
    factory { UserPreference(get()) }
}

val localDataStoreModule = module {
    factory { LocalDataStore(get()) }
}

val remoteDataSourceModule = module {
    factory { RemoteDataStore(androidApplication(), get()) }
}

val repositoryModule = module {
    single { LocalDataStore(get()) }
    single { RemoteDataStore(get(), get()) }
    single<IRepository> {
        Repository(get(), get())
    }
}

val usersUseCaseModule = module {
    factory<UsersUseCase> { UsersInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}