package com.example.syngrychallenge.data.remote.config

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class TmdbConfig {
    companion object {
        fun <T> getTmdbConfig(serviceClass: Class<T>): T {
            val loggingInterception =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterception)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(serviceClass)
        }
    }
}