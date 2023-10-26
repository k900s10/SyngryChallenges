package com.example.syngrychallenge.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity(tableName = "userNotes")
data class UserNotesEntity(
    @Embedded
    val user: UsersEntity,

    @Relation(
        parentColumn = "username",
        entityColumn = "FkUsername"
    )
    val notes : List<NotesEntity>
)
