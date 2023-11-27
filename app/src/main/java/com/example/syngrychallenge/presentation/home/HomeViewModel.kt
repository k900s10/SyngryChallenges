package com.example.syngrychallenge.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.model.NewMoviesModel
import com.example.core.domain.usecase.GetNewMoviesUseCase
import com.example.core.domain.usecase.GetPopularMoviesUseCase
import com.example.core.utils.result.GetMoviesResult

class HomeViewModel(
    getNewMoviesUseCase: GetNewMoviesUseCase,
    getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {
    val newMovies: LiveData<GetMoviesResult<List<NewMoviesModel>>> =
        getNewMoviesUseCase.getNewMovies().asLiveData()
    val popularMovies: LiveData<GetMoviesResult<List<NewMoviesModel>>> =
        getPopularMoviesUseCase.getPopularMovies().asLiveData()
}