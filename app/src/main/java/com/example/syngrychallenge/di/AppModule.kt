package com.example.syngrychallenge.di


import com.example.core.domain.usecase.UsersInteractor
import com.example.core.domain.usecase.UsersUseCase
import com.example.syngrychallenge.presentation.home.HomeViewModel
import com.example.syngrychallenge.presentation.login.LoginViewModel
import com.example.syngrychallenge.presentation.movieDetail.MovieDetailViewModel
import com.example.syngrychallenge.presentation.profile.ProfileViewModel
import com.example.syngrychallenge.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UsersUseCase> { UsersInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}


//val networkModule = module {
//    single {
//        Interceptor { chain ->
//            chain.proceed(
//                chain.request().newBuilder()
//                    .addHeader(Constant.ACCEPT_HEADER_LABEL, Constant.ACCEPT_HEADER_VALUE)
//                    .addHeader(
//                        Constant.AUTHORIZATION_HEADER_LABEL,
//                        Constant.AUTHORIZATION_HEADER_VALUE_HEADER + Constant.AUTHORIZATION_HEADER_VALUE
//                    )
//                    .build()
//            )
//        }
//    }
//    single {
//        OkHttpClient.Builder()
//            .addInterceptor(get<Interceptor>())
////            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//            .connectTimeout(120, TimeUnit.SECONDS)
//            .readTimeout(120, TimeUnit.SECONDS)
//            .build()
//    }
//    single {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(androidApplication().getString(R.string.api_url))
//            .addConverterFactory(MoshiConverterFactory.create())
//            .client(get())
//            .build()
//        retrofit.create(MoviesService::class.java)
//    }
//}
//
//val userDataStoreModule = module {
//    single { com.example.core.data.local.pref.UserDataStore(androidApplication().userDataStore) }
//}
//
//
//val localDataStoreModule = module {
//    factory { com.example.core.data.local.LocalDataStore(get()) }
//}
//
//val remoteDataSourceModule = module {
//    factory { RemoteDataStore( get()) }
//}
//
//val repositoryModule = module {
//    single { com.example.core.data.local.LocalDataStore(get()) }
//    single { RemoteDataStore( get()) }
//    single<IRepository> {
//        com.example.core.data.Repository(get(), get())
//    }
//}
//
