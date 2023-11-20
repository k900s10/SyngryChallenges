package com.example.syngrychallenge.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.remote.response.ApiResponse
import com.example.core.domain.model.NewMoviesModel
import com.example.core.domain.usecase.UsersUseCase

class HomeViewModel(useCase: UsersUseCase) : ViewModel() {
    val newMovies: LiveData<ApiResponse<List<NewMoviesModel>>> = useCase.getNewMovie().asLiveData()
    val popularMovies: LiveData<ApiResponse<List<NewMoviesModel>>> = useCase.getPopularMovie().asLiveData()
}