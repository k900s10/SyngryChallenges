package com.example.syngrychallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.syngrychallenge.data.local.entity.NotesEntity
import com.example.syngrychallenge.data.local.entity.UserNotesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Transaction
    @Query("SELECT * FROM users WHERE username = :username")
    fun getAllNote(username: String) : Flow<UserNotesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNote(notesEntity: NotesEntity)

    @Update
    fun updateNote(notesEntity: NotesEntity)

    @Delete
    suspend fun deleteNote(notesEntity: NotesEntity)
}