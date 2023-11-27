package com.example.core.di

import com.example.core.data.Repository
import com.example.core.data.local.LocalDataStore
import com.example.core.data.local.pref.UserDataStore
import com.example.core.data.local.pref.userDataStore
import com.example.core.data.remote.RemoteDataStore
import com.example.core.data.remote.services.MoviesService
import com.example.core.domain.repository.IRepository
import com.example.core.utils.CoreConstant
import com.example.core.utils.CoreConstant.AUTHORIZATION_HEADER_VALUE
import com.example.core.utils.CoreConstant.MOVIES_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        //add API key in utils/CoreConstant/AUTHORIZATION_HEADER_VALUE at core module before running the app.
        Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader(CoreConstant.ACCEPT_HEADER_LABEL, CoreConstant.ACCEPT_HEADER_VALUE)
                    .addHeader(
                        CoreConstant.AUTHORIZATION_HEADER_LABEL,
                        CoreConstant.AUTHORIZATION_HEADER_VALUE_HEADER + AUTHORIZATION_HEADER_VALUE
                    )
                    .build()
            )
        }
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(MOVIES_URL)
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
    factory { RemoteDataStore(get()) }
}

val repositoryModule = module {
    single { LocalDataStore(get()) }
    single { RemoteDataStore(get()) }
    single<IRepository> /*(named("repository"))*/ {
        Repository(get(), get(), get())
    }
}