package com.example.myapplication.ui.main.repository

import com.example.myapplication.room.User
import com.example.myapplication.room.UserDao

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override fun getAllUsers() = userDao.getAll()

    private fun fetchNewData(): List<User> {
        Thread.sleep(3000)
        val users = mutableListOf<User>()
        users.add(User(5, "Rishab", "Pant"))
        users.add(User(6, "KL", "Rahul"))
        return users
    }

    override fun insertUsers(users: List<User>) {
        userDao.insertAll(users)
    }

    override fun deleteAllUser() {
        userDao.deleteAll()
    }
}
