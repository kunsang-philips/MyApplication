package com.example.myapplication.ui.main

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.myapplication.R
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.User
import kotlinx.android.synthetic.main.activity_room_d_b.buttonDeleteAllUsers
import kotlinx.android.synthetic.main.activity_room_d_b.buttonFetch
import kotlinx.android.synthetic.main.activity_room_d_b.textView

class RoomDBActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_d_b)
        initializeRoomDB()
        buttonFetch.setOnClickListener {
            textView.text = ""
            AsyncTask.execute {
                insertUsers()
                displayUser()
            }
        }
        buttonDeleteAllUsers.setOnClickListener {
            textView.text = ""
            AsyncTask.execute {
                db.userDao().deleteAll()
                displayUser()
            }
        }
    }

    private fun displayUser() {
        db.userDao().getAll().forEach {
            runOnUiThread {
                textView.append("${it.firstName} ${it.lastName}\n")
            }
        }
    }

    private fun insertUsers() {
        val list = mutableListOf<User>()
        list.add(User(1, "Virat", "Kohli"))
        list.add(User(2, "Rohit", "Sharma"))
        db.userDao().insertAll(list)
    }

    private fun initializeRoomDB() {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }
}
