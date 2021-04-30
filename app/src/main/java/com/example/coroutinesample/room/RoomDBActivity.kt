package com.example.coroutinesample.room

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.coroutinesample.R
import kotlinx.android.synthetic.main.activity_room_db.buttonDeleteAllUsers
import kotlinx.android.synthetic.main.activity_room_db.buttonFetch
import kotlinx.android.synthetic.main.activity_room_db.textView
import kotlinx.coroutines.launch

class RoomDBActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_db)
        initializeRoomDB()
        buttonFetch.setOnClickListener {
            textView.text = ""
            lifecycleScope.launch {
                insertUsers()
                displayUser()
            }
        }
        buttonDeleteAllUsers.setOnClickListener {
            textView.text = ""
            lifecycleScope.launch {
                db.userDaoForRoom().deleteAll()
                displayUser()
            }
        }
    }

    private suspend fun displayUser() {
        db.userDaoForRoom().getAll().forEach {
            textView.append("${it.firstName} ${it.lastName}\n")
        }
    }

    private suspend fun insertUsers() {
        val list = mutableListOf<User>()
        list.add(User(1, "Virat", "Kohli"))
        list.add(User(2, "Rohit", "Sharma"))
        db.userDaoForRoom().insertAll(list)
    }

    private fun initializeRoomDB() {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }
}
