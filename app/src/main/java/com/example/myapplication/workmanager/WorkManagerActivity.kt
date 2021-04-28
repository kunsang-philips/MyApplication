package com.example.myapplication.workmanager

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_work_manager.buttonEnqueue
import kotlinx.android.synthetic.main.activity_work_manager.buttonStop

class WorkManagerActivity : AppCompatActivity() {
    var myWorkManager: WorkManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
        val workRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .addTag("mywork")
            .build()
        buttonEnqueue.setOnClickListener {
            myWorkManager = WorkManager.getInstance(this)
            myWorkManager?.enqueue(workRequest)
        }

        buttonStop.setOnClickListener {
            myWorkManager?.cancelAllWorkByTag("mywork")
        }
    }
}
