package com.example.syngrychallenge.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.syngrychallenge.domain.model.NoteModel
import com.example.syngrychallenge.domain.usecase.NoteUseCase

class CreateNoteViewModel(private val noteUseCase: NoteUseCase): ViewModel() {
    fun createNote(username: String, title: String, note: String) {
        val notesModel = NoteModel(
            title = title,
            note = note,
            fkUsername = username
        )
        noteUseCase.createNote(notesModel)
    }
}