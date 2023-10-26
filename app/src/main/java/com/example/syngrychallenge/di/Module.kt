package com.example.syngrychallenge.di

import androidx.room.Room
import com.example.syngrychallenge.data.Repository
import com.example.syngrychallenge.data.local.LocalDataStore
import com.example.syngrychallenge.data.local.room.NotesDatabase
import com.example.syngrychallenge.data.local.room.UsersDatabase
import com.example.syngrychallenge.domain.repository.IRepository
import com.example.syngrychallenge.domain.usecase.NoteInteractor
import com.example.syngrychallenge.domain.usecase.NoteUseCase
import com.example.syngrychallenge.domain.usecase.UsersInteractor
import com.example.syngrychallenge.domain.usecase.UsersUseCase
import com.example.syngrychallenge.ui.viewModel.CreateNoteViewModel
import com.example.syngrychallenge.ui.viewModel.DeleteNoteViewModel
import com.example.syngrychallenge.ui.viewModel.EditNoteViewModel
import com.example.syngrychallenge.ui.viewModel.HomeViewModel
import com.example.syngrychallenge.ui.viewModel.LoginViewModel
import com.example.syngrychallenge.ui.viewModel.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notesDatabaseModule = module {
    factory { get<NotesDatabase>().dao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            NotesDatabase::class.java,
            "Note.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val usersDatabaseModule = module {
    factory { get<UsersDatabase>().dao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            UsersDatabase::class.java,
            "Users.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val repositoryModule = module {
    single { LocalDataStore(get(), get()) }
    single<IRepository> {
        Repository(get())
    }
}

val noteUseCaseModule = module {
    factory<NoteUseCase> { NoteInteractor(get()) }
}

val usersUseCaseModule = module {
    factory<UsersUseCase> { UsersInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { CreateNoteViewModel(get()) }
    viewModel { EditNoteViewModel(get()) }
    viewModel { DeleteNoteViewModel(get()) }
}