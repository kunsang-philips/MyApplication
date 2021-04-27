package com.example.myapplication.workmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_work_manager.buttonEnqueue

class WorkManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
        val workRequest = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
        buttonEnqueue.setOnClickListener {
            WorkManager.getInstance(this).enqueue(workRequest)
        }
    }
}
