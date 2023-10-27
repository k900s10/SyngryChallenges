package com.example.syngrychallenge.domain.repository

import com.example.syngrychallenge.domain.model.NoteModel
import com.example.syngrychallenge.domain.model.UsersModel
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getAllNotes(username: String): Flow<List<NoteModel>>

    fun createNote(noteModel: NoteModel)

    fun updateNote(noteModel: NoteModel)

    fun createAccount(usersModel: UsersModel)

    suspend fun isAccountExist(usersModel: UsersModel): String

    fun deleteNote(noteModel: NoteModel)
}