package com.example.coroutinesample.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coroutinesample.livedata.UserDaoForLiveData
import com.example.coroutinesample.workmanager.UserDaoForWorkManager

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userDaoWithLiveData(): UserDaoForLiveData
    abstract fun userDaoForWorkManager(): UserDaoForWorkManager
}
