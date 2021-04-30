package com.example.coroutinesample.workmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.room.Room
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.coroutinesample.R
import com.example.coroutinesample.room.AppDatabase
import com.example.coroutinesample.room.User
import kotlinx.coroutines.delay

class MyWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {
    private lateinit var db: AppDatabase

    override suspend fun doWork(): Result {
        initializeRoomDB()
        dbOperation()
        networkOperation()
        displayNotification(getUsers())
        return Result.success()
    }

    private suspend fun networkOperation() {
        delay(2000)
        val users = mutableListOf<User>()
        users.add(User(4, "Sachin", "Tendulkar"))
        users.add(User(5, "Rahul", "Dravid"))
        insertUsers(users)
    }

    private suspend fun dbOperation() {
        val users = mutableListOf<User>()
        users.add(User(1, "Virat", "Kohli"))
        users.add(User(2, "Rohit", "Sharma"))
        insertUsers(users)
        delay(2000)
    }

    private fun displayNotification(task: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "simplifiedcoding",
                "simplifiedcoding",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        val notification = NotificationCompat.Builder(
            getApplicationContext(),
            "simplifiedcoding"
        )
            .setContentTitle("Cricketers")
            .setContentText(task)
            .setSmallIcon(R.mipmap.ic_launcher)
        notificationManager.notify(1, notification.build())
    }

    private fun initializeRoomDB() {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    private suspend fun getUsers(): String {
        var text = ""
        db.userDaoForWorkManager().getAll().forEach {
            text += ("${it.firstName}  ${it.lastName} ,\n")
        }
        return text
    }

    private suspend fun insertUsers(users: List<User>) {
        db.userDao().insertAll(users)
    }
}
