package com.example.syngrychallenge.data

import com.example.syngrychallenge.data.local.LocalDataStore
import com.example.syngrychallenge.data.local.entity.UsersEntity
import com.example.syngrychallenge.domain.model.NoteModel
import com.example.syngrychallenge.domain.model.UsersModel
import com.example.syngrychallenge.domain.repository.IRepository
import com.example.syngrychallenge.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class Repository(private val localDataStore: LocalDataStore) : IRepository {

    override fun getAllNotes(username: String): Flow<List<NoteModel>> =
        localDataStore.getAllNote(username).map { userNotes ->
            DataMapper.mapNotesEntityToDomain(userNotes.notes)
        }


    override fun createNote(noteModel: NoteModel) {
        val entity = DataMapper.mapNotesDomainToEntity(noteModel)

        CoroutineScope(Dispatchers.IO).launch {
            localDataStore.createNote(entity)
        }
    }

    override fun updateNote(noteModel: NoteModel) {
        val entity = DataMapper.mapNotesDomainToEntity(noteModel)

        CoroutineScope(Dispatchers.IO).launch {
            localDataStore.updateNote(entity)
        }
    }

    override fun deleteNote(noteModel: NoteModel) {
        val data = DataMapper.mapNotesDomainToEntity(noteModel)

        CoroutineScope(Dispatchers.IO).launch {
            localDataStore.deleteNote(data)
        }
    }

    override fun createAccount(usersModel: UsersModel) {
        val data = DataMapper.mapUsersDomainToEntity(usersModel)
        CoroutineScope(Dispatchers.IO).launch {
            localDataStore.createAccount(data)
        }
    }

    override suspend fun isAccountExist(usersModel: UsersModel): String {
        val email = usersModel.email
        val password = usersModel.password
        val account : UsersEntity? = CoroutineScope(Dispatchers.IO).async {
            localDataStore.isAccountExist(email, password)
        }.await()

        return account?.username ?: ""
    }
}