package com.example.core.di

import com.example.core.data.remote.services.MoviesService
import com.example.core.utils.CoreConstant
import com.example.core.utils.CoreConstant.AUTHORIZATION_HEADER_VALUE
import com.example.core.utils.CoreConstant.MOVIES_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

//val repositoryModule = module {
//    single { LocalDataStore(get()) }
//    single { RemoteDataStore(get()) }
//    single<IRepository> /*(named("repository"))*/ {
//        Repository(get(), get(), WorkManager.getInstance(androidApplication()))
//    }
//}