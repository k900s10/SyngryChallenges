package com.example.syngrychallenge.di

import com.example.syngrychallenge.R
import com.example.syngrychallenge.data.Repository
import com.example.syngrychallenge.data.local.LocalDataStore
import com.example.syngrychallenge.data.local.pref.UserDataStore
import com.example.syngrychallenge.data.local.pref.userDataStore
import com.example.syngrychallenge.data.remote.RemoteDataStore
import com.example.syngrychallenge.data.remote.services.MoviesService
import com.example.syngrychallenge.domain.repository.IRepository
import com.example.syngrychallenge.domain.usecase.UsersInteractor
import com.example.syngrychallenge.domain.usecase.UsersUseCase
import com.example.syngrychallenge.presentation.home.HomeViewModel
import com.example.syngrychallenge.presentation.login.LoginViewModel
import com.example.syngrychallenge.presentation.movieDetail.MovieDetailViewModel
import com.example.syngrychallenge.presentation.profile.ProfileViewModel
import com.example.syngrychallenge.presentation.register.RegisterViewModel
import com.example.syngrychallenge.utils.Constant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader(Constant.ACCEPT_HEADER_LABEL, Constant.ACCEPT_HEADER_VALUE)
                    .addHeader(
                        Constant.AUTHORIZATION_HEADER_LABEL,
                        Constant.AUTHORIZATION_HEADER_VALUE_HEADER + Constant.AUTHORIZATION_HEADER_VALUE
                    )
                    .build()
            )
        }
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
//            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
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

val userDataStoreModule = module {
    single { UserDataStore(androidApplication().userDataStore) }
}


val localDataStoreModule = module {
    factory { LocalDataStore(get()) }
}

val remoteDataSourceModule = module {
    factory { RemoteDataStore( get()) }
}

val repositoryModule = module {
    single { LocalDataStore(get()) }
    single { RemoteDataStore( get()) }
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

