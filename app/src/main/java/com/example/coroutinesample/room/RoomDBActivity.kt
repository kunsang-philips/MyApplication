package com.example.coroutinesample.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.coroutinesample.R
import kotlinx.android.synthetic.main.activity_room_d_b.textView
import kotlinx.coroutines.launch

class RoomDBActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_d_b)
        initializeRoomDB()
        lifecycleScope.launch {
            insertUsers()
            displayUser()
        }
    }

    private fun displayUser() {
        db.userDao().getAll().value?.forEach {
            textView.append("${it.firstName} ${it.lastName}\n")
        }
    }

    private suspend fun insertUsers() {
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
