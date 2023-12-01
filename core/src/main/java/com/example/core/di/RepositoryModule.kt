package com.example.core.di

import com.example.core.data.local.LocalRepositoryImp
import com.example.core.data.remote.RemoteRepositoryImp
import com.example.core.domain.repository.LocalRepository
import com.example.core.domain.repository.RemoteRepository
import org.koin.dsl.module

val localRepositoryModule = module {
    single<LocalRepository> { LocalRepositoryImp(get()) }
}
val remoteRepositoryModule = module {
    single<RemoteRepository> { RemoteRepositoryImp(get()) }
}