package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_basics.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class GlobalScopeActivity : AppCompatActivity() {
    var parentJob: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_scope)
        //https://www.youtube.com/watch?v=0pYHG0MLU2w
        // Job = A background job. Conceptually, a job is a cancellable thing with a life-cycle that culminates in its completion.
        // if Parent job cancelled, then children jobs also should cancelled.
        button.setOnClickListener {
            if (button.text.toString().equals("Start", true)) {
                button.text = "Stop"
                textView.text = "Job started"
                parentJob = CoroutineScope(Main).launch {
                    launch {
                        doSomeLongRunningWork1("Work1")
                    }
                    GlobalScope.launch {
                        doSomeLongRunningWork2("Work2")
                    }
                }
            } else {
                button.text = "Start"
                CoroutineScope(Main).launch {
                    parentJob?.cancelAndJoin()
                }
            }
            parentJob?.invokeOnCompletion {
                if (it != null) {
                    textView.text = "Job was cancelled"
                } else {
                    textView.text = "Job Completed"
                    button.text = "Start"
                }
            }
        }
    }

    suspend fun doSomeLongRunningWork1(workName: String) {
        delay(3000)
        Log.d("FAFA", "Work1 done $workName")
    }

    suspend fun doSomeLongRunningWork2(workName: String) {
        delay(3000)
        Log.d("FAFA", "Work2 done $workName")
    }
}