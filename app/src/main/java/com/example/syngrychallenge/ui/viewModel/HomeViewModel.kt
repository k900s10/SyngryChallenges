package com.example.syngrychallenge.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.syngrychallenge.domain.model.NoteModel
import com.example.syngrychallenge.domain.usecase.NoteUseCase

class HomeViewModel(private val noteUseCase: NoteUseCase) : ViewModel() {
    val getAllNotes = noteUseCase.getAllNotes().asLiveData()


    fun createNote( title: String, note: String) {
        val noteModel = NoteModel(
            title = title,
            note = note
        )
        noteUseCase.createNote(noteModel)
    }

    fun updateNote(id: Int, title: String, note: String) {
        val noteModel = NoteModel(
            id = id,
            title = title,
            note = note
        )
        noteUseCase.updateNote(noteModel)
    }
}