package com.example.myapplication.ui.main.repository

import com.example.myapplication.room.User

interface UserRepository {
    fun getAllUsers(): List<User>
    fun insertUsers(users: List<User>)
    fun deleteAllUser()
}
