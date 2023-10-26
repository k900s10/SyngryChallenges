package com.example.syngrychallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.syngrychallenge.data.local.entity.UsersEntity

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun createAccount(usersEntity: UsersEntity)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun isAccountExist(email: String, password: String) :
//            List<UsersEntity>
            UsersEntity
}