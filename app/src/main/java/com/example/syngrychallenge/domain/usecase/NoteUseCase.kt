package com.example.syngrychallenge.domain.usecase

import com.example.syngrychallenge.domain.model.NoteModel
import kotlinx.coroutines.flow.Flow

interface NoteUseCase {
    fun getAllNotes(username: String): Flow<List<NoteModel>>

    fun createNote(noteModel: NoteModel)

    fun updateNote(noteModel: NoteModel)

    fun deleteNote(noteModel: NoteModel)
}