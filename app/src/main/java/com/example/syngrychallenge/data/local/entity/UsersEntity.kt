package com.example.syngrychallenge.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UsersEntity(
    //TODO() change the PK from username to email.
    //TODO() semua user masih dapat mengakses semua data

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "password")
    val password: String
)