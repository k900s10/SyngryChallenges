package com.example.syngrychallenge.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.syngrychallenge.data.remote.response.ApiResponse
import com.example.syngrychallenge.domain.model.CastsModel
import com.example.syngrychallenge.domain.usecase.UsersUseCase

class MovieDetailViewModel(private val useCase: UsersUseCase) : ViewModel() {
    fun movieCasts(movieId: Int): LiveData<ApiResponse<List<CastsModel>>> =
        useCase.getMovieCasts(movieId).asLiveData()
}