package com.example.coroutinesample.workmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.coroutinesample.R
import kotlinx.android.synthetic.main.activity_workmanager.buttonEnqueue

class WorkmanagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workmanager)
        val workRequest = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
        buttonEnqueue.setOnClickListener {
            WorkManager.getInstance(this).enqueue(workRequest)
        }
    }
}
