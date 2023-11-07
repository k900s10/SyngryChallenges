package com.example.syngrychallenge.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.syngrychallenge.data.remote.response.ApiResponse
import com.example.syngrychallenge.domain.model.NewMoviesModel
import com.example.syngrychallenge.domain.usecase.UsersUseCase
import kotlinx.coroutines.flow.Flow

class HomeViewModel(useCase: UsersUseCase) : ViewModel() {
    val newMovies: LiveData<ApiResponse<List<NewMoviesModel>>> = useCase.getNewMovie().asLiveData()
    val popularMovies: LiveData<ApiResponse<List<NewMoviesModel>>> = useCase.getPopularMovie().asLiveData()
}