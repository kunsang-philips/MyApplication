package com.example.myapplication.repository

import androidx.lifecycle.liveData
import com.example.myapplication.livedata.UserDaoForLiveData
import com.example.myapplication.room.User
import kotlinx.coroutines.delay

class UserRepositoryImpl(private val userDaoForLiveData: UserDaoForLiveData) : UserRepository {
    override fun getAllUsers() = liveData {
        emitSource(userDaoForLiveData.getAll())
        val users = fetchNewData()
        userDaoForLiveData.insertAll(users)
    }

    private suspend fun fetchNewData(): List<User> {
        delay(3000)
        val users = mutableListOf<User>()
        users.add(User(5, "Rishab", "Pant"))
        users.add(User(6, "KL", "Rahul"))
        return users
    }

    override suspend fun insertUsers(users: List<User>) {
        userDaoForLiveData.insertAll(users)
    }

    override suspend fun deleteAllUser() {
        userDaoForLiveData.deleteAll()
    }
}
