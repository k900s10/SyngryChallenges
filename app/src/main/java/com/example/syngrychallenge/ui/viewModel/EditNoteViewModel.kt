package com.example.syngrychallenge.ui.viewModel


import androidx.lifecycle.ViewModel
import com.example.syngrychallenge.domain.model.NoteModel
import com.example.syngrychallenge.domain.usecase.NoteUseCase

class EditNoteViewModel(private val noteUseCase: NoteUseCase): ViewModel() {
    fun updateNote(username: String, noteId: Int, title: String, note: String) {
        val notesModel = NoteModel(
            id = noteId,
            title = title,
            note = note,
            fkUsername = username
        )
        noteUseCase.updateNote(notesModel)
    }
}