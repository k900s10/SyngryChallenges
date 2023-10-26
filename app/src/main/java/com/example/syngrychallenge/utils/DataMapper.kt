package com.example.syngrychallenge.utils

import com.example.syngrychallenge.data.local.entity.NotesEntity
import com.example.syngrychallenge.data.local.entity.UsersEntity
import com.example.syngrychallenge.domain.model.NoteModel
import com.example.syngrychallenge.domain.model.UsersModel

object DataMapper {
    fun mapNotesEntityToDomain(data: List<NotesEntity>): List<NoteModel> =
        data.map { entity ->
            NoteModel(
                id = entity.id,
                title = entity.title,
                note = entity.note
            )
        }

    fun mapNotesDomainToEntity(domain: NoteModel): NotesEntity =
        NotesEntity(
            id = domain.id,
            title = domain.title,
            note = domain.note
        )

    fun mapUsersDomainToEntity(domain: UsersModel): UsersEntity =
        UsersEntity(
            username = domain.username,
            email = domain.email,
            password = domain.password
        )

    fun mapUsersEntityToDomain(domain: UsersEntity): UsersModel =
        UsersModel(
            username = domain.username,
            email = domain.email,
            password = domain.password
        )

}