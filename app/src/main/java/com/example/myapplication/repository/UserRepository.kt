package com.example.myapplication.repository

import com.example.myapplication.room.User

interface UserRepository {
    fun getAllUsers(): List<User>
    fun insertUsers(users: List<User>)
    fun deleteAllUser()
}
