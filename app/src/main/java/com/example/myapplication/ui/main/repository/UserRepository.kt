package com.example.myapplication.ui.main.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.room.User

interface UserRepository {
    fun getAllUsers(): LiveData<List<User>>
    suspend fun insertUsers(users: List<User>)
    suspend fun deleteAllUser()
}
