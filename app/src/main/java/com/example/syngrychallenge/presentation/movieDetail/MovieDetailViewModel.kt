package com.example.syngrychallenge.presentation.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.remote.response.ApiResponse
import com.example.core.domain.model.CastsModel
import com.example.core.domain.usecase.UsersUseCase

class MovieDetailViewModel(private val useCase: UsersUseCase) : ViewModel() {
    fun movieCasts(movieId: Int): LiveData<ApiResponse<List<CastsModel>>> =
        useCase.getMovieCasts(movieId).asLiveData()

}