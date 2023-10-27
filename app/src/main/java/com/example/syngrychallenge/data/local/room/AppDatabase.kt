package com.example.syngrychallenge.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.syngrychallenge.data.local.dao.NotesDao
import com.example.syngrychallenge.data.local.dao.UsersDao
import com.example.syngrychallenge.data.local.entity.NotesEntity
import com.example.syngrychallenge.data.local.entity.UsersEntity

@Database(entities = [NotesEntity::class, UsersEntity::class], version = 3, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun notesDao() : NotesDao

    abstract fun usersDao() : UsersDao
}