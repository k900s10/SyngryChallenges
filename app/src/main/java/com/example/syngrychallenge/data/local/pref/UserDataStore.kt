package com.example.syngrychallenge.data.local.pref

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.syngrychallenge.domain.model.LoginModel
import com.example.syngrychallenge.domain.model.ProfileModel
import com.example.syngrychallenge.domain.model.RegisterModel
import com.example.syngrychallenge.utils.Constant.USER_PREF
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(USER_PREF)

class UserDataStore(private val dataStore: DataStore<Preferences>) {
    private val IS_LOGIN_KEY = booleanPreferencesKey("is_login_key")
    private val USERNAME_KEY = stringPreferencesKey("username_key")
    private val EMAIL_KEY = stringPreferencesKey("email_key")
    private val PASSWORD_KEY = stringPreferencesKey("password_key")
    private val NAME_KEY = stringPreferencesKey("name_key")
    private val BIRTHDAY_KEY = stringPreferencesKey("birthday_key")
    private val ADDRESS_KEY = stringPreferencesKey("address_key")

    val isLogin: Flow<Boolean> = dataStore.data.map { preference ->
        preference[IS_LOGIN_KEY] ?: false
    }

    fun getProfile(): Flow<ProfileModel> = dataStore.data.map { preference ->
        ProfileModel(
            username = preference[USERNAME_KEY],
            name = preference[NAME_KEY],
            birthday = preference[BIRTHDAY_KEY],
            address = preference[ADDRESS_KEY]
        )
    }

    suspend fun auth(): LoginModel =
        LoginModel(
            email = dataStore.data.first()[EMAIL_KEY],
                password = dataStore.data.first()[PASSWORD_KEY]
            )


    suspend fun createAccount(registerModel: RegisterModel) {
        dataStore.edit { preference ->
            preference[USERNAME_KEY] = registerModel.username
            preference[EMAIL_KEY] = registerModel.email
            preference[PASSWORD_KEY] = registerModel.password
        }
    }

    suspend fun createLoginSession() {
        dataStore.edit { preference ->
            preference[IS_LOGIN_KEY] = true
        }
    }

    suspend fun logout() {
        dataStore.edit { preference ->
            preference[IS_LOGIN_KEY] = false
        }
    }

    suspend fun updateProfile(input: ProfileModel) {
        dataStore.edit { preference ->
            preference[USERNAME_KEY] = input.username.toString()
            preference[NAME_KEY] = input.name.toString()
            preference[BIRTHDAY_KEY] = input.birthday.toString()
            preference[ADDRESS_KEY] = input.address.toString()
        }
    }
}