package com.example.myapplication.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.livedata.UserDaoForLiveData
import com.example.myapplication.workmanager.UserDaoForWorkManager

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userDaoWithLiveData(): UserDaoForLiveData
    abstract fun userDaoForWorkManager(): UserDaoForWorkManager
}
