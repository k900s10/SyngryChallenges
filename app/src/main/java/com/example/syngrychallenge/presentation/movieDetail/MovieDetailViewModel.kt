package com.example.syngrychallenge.presentation.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.model.CastsModel
import com.example.core.domain.usecase.GetMovieCastsUseCase
import com.example.core.utils.result.GetCastsResult

class MovieDetailViewModel(private val movieCastsUseCase: GetMovieCastsUseCase) : ViewModel() {
    fun movieCasts(movieId: Int): LiveData<GetCastsResult<List<CastsModel>>> =
        movieCastsUseCase.getMovieCasts(movieId).asLiveData()

}