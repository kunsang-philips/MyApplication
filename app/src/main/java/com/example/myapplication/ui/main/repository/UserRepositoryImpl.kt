package com.example.myapplication.ui.main.repository

import androidx.lifecycle.liveData
import com.example.myapplication.room.User
import com.example.myapplication.room.UserDao
import kotlinx.coroutines.delay

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override fun getAllUsers() = liveData {
        emitSource(userDao.getAll())
        val users = fetchNewData()
        userDao.insertAll(users)
    }

    private suspend fun fetchNewData(): List<User> {
        delay(3000)
        val users = mutableListOf<User>()
        users.add(User(5, "Rishab", "Pant"))
        users.add(User(6, "KL", "Rahul"))
        return users
    }

    override suspend fun insertUsers(users: List<User>) {
        userDao.insertAll(users)
    }

    override suspend fun deleteAllUser() {
        userDao.deleteAll()
    }
}
