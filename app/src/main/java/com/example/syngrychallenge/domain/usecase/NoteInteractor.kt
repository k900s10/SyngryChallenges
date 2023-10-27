package com.example.syngrychallenge.domain.usecase

import com.example.syngrychallenge.domain.model.NoteModel
import com.example.syngrychallenge.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class NoteInteractor(private val iRepository: IRepository): NoteUseCase {
    override fun getAllNotes(username: String): Flow<List<NoteModel>> =
        iRepository.getAllNotes(username)

    override fun createNote(noteModel: NoteModel) =
        iRepository.createNote(noteModel)

    override fun updateNote(noteModel: NoteModel) =
        iRepository.updateNote(noteModel)

    override fun deleteNote(noteModel: NoteModel) =
        iRepository.deleteNote(noteModel)
}