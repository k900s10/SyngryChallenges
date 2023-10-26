package com.example.syngrychallenge.data.local

import com.example.syngrychallenge.data.local.dao.NotesDao
import com.example.syngrychallenge.data.local.dao.UsersDao
import com.example.syngrychallenge.data.local.entity.NotesEntity
import com.example.syngrychallenge.data.local.entity.UsersEntity
import kotlinx.coroutines.flow.Flow

class LocalDataStore(private val notes: NotesDao, private val users: UsersDao) {
    fun getAllNote() : Flow<List<NotesEntity>> =
        notes.getAllNote()

    suspend fun createNote(notesEntity: NotesEntity) =
        notes.createNote(notesEntity)

    fun updateNote(notesEntity: NotesEntity) =
        notes.updateNote(notesEntity)

    suspend fun createAccount(usersEntity: UsersEntity) =
        users.createAccount(usersEntity)

    suspend fun isAccountExist(email: String, password: String) :
//            List<UsersEntity> =
            UsersEntity =
        users.isAccountExist(email, password)

    suspend fun deleteNote(notesEntity: NotesEntity) =
        notes.deleteNote(notesEntity)

}