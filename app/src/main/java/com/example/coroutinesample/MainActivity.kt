package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutinesample.R
import com.example.myapplication.room.RoomDBActivity
import com.example.myapplication.workmanager.WorkManagerActivity
import kotlinx.android.synthetic.main.activity_main.roomDB
import kotlinx.android.synthetic.main.activity_main.workManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        roomDB.setOnClickListener {
            startActivity(Intent(this, RoomDBActivity::class.java))
        }
        workManager.setOnClickListener {
            startActivity(Intent(this, WorkManagerActivity::class.java))
        }
    }
}
