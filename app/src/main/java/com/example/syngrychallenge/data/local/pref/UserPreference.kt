package com.example.syngrychallenge.data.local.pref

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.syngrychallenge.domain.model.LoginModel
import com.example.syngrychallenge.domain.model.ProfileModel
import com.example.syngrychallenge.domain.model.RegisterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class UserPreference(context: Context) {

    private var pref: SharedPreferences =
        context.getSharedPreferences("Session", Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = pref.edit()

    val isLogin: Boolean = pref.getBoolean(IS_LOGIN_KEY, false)

    fun getProfile(): Flow<ProfileModel> = flow {

        val username = pref.getString(USERNAME_KEY, "")
        val name = pref.getString(NAME_KEY, "")
        val birthday = pref.getString(BIRTHDAY_KEY, "")
        val address = pref.getString(ADDRESS_KEY, "")
        emit(
            ProfileModel(
                username = username,
                name = name,
                birthday = birthday,
                address = address
            )
        )
    }

    fun auth(): LoginModel {
        val email = pref.getString(EMAIL_KEY, "")
        val password = pref.getString(PASSWORD_KEY, "")

        return LoginModel(email, password)
    }

    fun createAccount(registerModel: RegisterModel) {
        editor.putString(USERNAME_KEY, registerModel.username)
        editor.putString(EMAIL_KEY, registerModel.email)
        editor.putString(PASSWORD_KEY, registerModel.password)
        editor.apply()
    }

    fun createLoginSession() {
        editor.putBoolean(IS_LOGIN_KEY, true)
            .apply()
    }

    fun logout() {
        editor.putBoolean(IS_LOGIN_KEY, false)
        editor.commit()
    }

    fun updateProfile(data: ProfileModel) {
        editor.putString(USERNAME_KEY, data.username)
        editor.putString(NAME_KEY, data.name)
        editor.putString(BIRTHDAY_KEY, data.birthday)
        editor.putString(ADDRESS_KEY, data.address)
        editor.apply()
    }


    companion object {
        private const val USERNAME_KEY = "username"
        private const val EMAIL_KEY = "email"
        private const val PASSWORD_KEY = "password"
        private const val NAME_KEY = "name"
        private const val BIRTHDAY_KEY = "birthday"
        private const val ADDRESS_KEY = "address"
        private const val IS_LOGIN_KEY = "isLogin"
    }

}