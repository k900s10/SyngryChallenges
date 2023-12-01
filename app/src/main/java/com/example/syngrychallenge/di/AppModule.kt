package com.example.syngrychallenge.di


import com.example.syngrychallenge.presentation.home.HomeViewModel
import com.example.syngrychallenge.presentation.login.LoginViewModel
import com.example.syngrychallenge.presentation.movieDetail.MovieDetailViewModel
import com.example.syngrychallenge.presentation.profile.ProfileViewModel
import com.example.syngrychallenge.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { ProfileViewModel(get(), get(), get(), get()) }
    viewModel { MovieDetailViewModel(get()) }
}