package com.example.syngrychallenge.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.syngrychallenge.domain.model.NoteModel
import com.example.syngrychallenge.domain.usecase.NoteUseCase

class DeleteNoteViewModel(private val noteUseCase: NoteUseCase): ViewModel() {
    fun deleteNote(noteModel : NoteModel) {
        noteUseCase.deleteNote(noteModel)
    }
}