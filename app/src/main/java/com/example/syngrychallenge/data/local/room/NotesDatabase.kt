package com.example.syngrychallenge.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.syngrychallenge.data.local.dao.NotesDao
import com.example.syngrychallenge.data.local.entity.NotesEntity

@Database(entities = [NotesEntity::class], version = 1, exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun dao() : NotesDao
}