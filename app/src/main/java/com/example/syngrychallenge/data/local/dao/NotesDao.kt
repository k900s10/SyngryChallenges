package com.example.syngrychallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.syngrychallenge.data.local.entity.NotesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    fun getAllNote() : Flow<List<NotesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNote(notesEntity: NotesEntity)

    @Update
    fun updateNote(notesEntity: NotesEntity)

    @Delete
    suspend fun deleteNote(notesEntity: NotesEntity)
}