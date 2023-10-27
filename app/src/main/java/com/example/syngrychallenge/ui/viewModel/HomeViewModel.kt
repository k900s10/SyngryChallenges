package com.example.syngrychallenge.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.syngrychallenge.domain.usecase.NoteUseCase

class HomeViewModel(private val noteUseCase: NoteUseCase) : ViewModel() {

    fun getAllNotes(username: String) =
        noteUseCase.getAllNotes(username).asLiveData()
}