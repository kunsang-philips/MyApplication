package com.example.coroutinesample.repository

import com.example.coroutinesample.room.User

interface UserRepository {
    fun getAllUsers(): List<User>
    fun insertUsers(users: List<User>)
    fun deleteAllUser()
}
