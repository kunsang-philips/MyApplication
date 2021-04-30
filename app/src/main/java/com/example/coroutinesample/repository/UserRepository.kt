package com.example.coroutinesample.repository

import androidx.lifecycle.LiveData
import com.example.coroutinesample.room.User

interface UserRepository {
    fun getAllUsers(): LiveData<List<User>>
    suspend fun insertUsers(users: List<User>)
    suspend fun deleteAllUser()
}
