package com.example.coroutinesample.scopes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.coroutinesample.R
import kotlinx.android.synthetic.main.activity_basics.button
import kotlinx.android.synthetic.main.activity_basics.textView
import kotlinx.android.synthetic.main.activity_global_scope.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class GlobalScopeActivity : AppCompatActivity() {
    var parentJob: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_scope)
        // https://www.youtube.com/watch?v=0pYHG0MLU2w
        // if Parent job cancelled, then children jobs also should cancelled.
        button.setOnClickListener {
            work1.text = "lifecycleScope work started..."
            work2.text = "GlobalScope work started..."
            if (button.text.toString().equals("Start", true)) {
                button.text = "Stop"
                textView.text = "Job started"
                parentJob = lifecycleScope.launch {
                    launch(Main) {
                        doSomeLongRunningWork1("Coroutine Scope work")
                    }
                    val data = GlobalScope.launch {
                        doSomeLongRunningWork2("Global Scope work")
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
        work1.text = "lifecycleScope work done"
    }

    suspend fun doSomeLongRunningWork2(workName: String) {
        delay(3000)
        withContext(Main) {
            work2.text = "GlobalScope work done"
        }
    }
}
