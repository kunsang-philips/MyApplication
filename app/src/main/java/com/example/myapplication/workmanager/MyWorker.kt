package com.example.myapplication.workmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.room.Room
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.myapplication.R
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MyWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    private lateinit var db: AppDatabase

    override fun doWork(): Result {
        CoroutineScope(IO).launch {
            doSomeDBOperation()
        }
        return Result.success()
    }

    private suspend fun doSomeDBOperation() {
        initializeRoomDB()
        insertUsers()
        displayNotification("My Worker", getUsers())
    }

    private fun displayNotification(title: String, task: String) {
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
            .setContentTitle(title)
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
        db.userDao().getAll().value?.forEach {
            text += ("${it.firstName} ${it.lastName}\n")
        }
        return text
    }

    private suspend fun insertUsers() {
        val list = mutableListOf<User>()
        list.add(User(1, "Virat", "Kohli"))
        list.add(User(2, "Rohit", "Sharma"))
        db.userDao().insertAll(list)
    }
}
