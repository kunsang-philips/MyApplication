package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_sequential.*
import kotlinx.coroutines.*

class ParallelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parallel)
        // firstApproach()
        secondApproach()
    }

    private fun secondApproach() {
        start.setOnClickListener {
            start.text = "Start"
            firstTextView.text = "FirstTextView"
            secondTextView.text = "SecondTextView"

            lifecycleScope.launch {
                val firstTask = async { doSomeWork(firstTextView, 5) }
                val secondTask = async { doSomeWork(secondTextView, 10) }
                firstTask.await()
                secondTask.await()
                start.text = "End"
            }
        }
    }

    private fun firstApproach() {
        start.setOnClickListener {
            start.text = "Start"
            firstTextView.text = "FirstTextView"
            secondTextView.text = "SecondTextView"
            Log.d("FAFA", "Before count thread: ${Thread.currentThread().name}")
            lifecycleScope.launch {
                val job1 = launch {
                    doSomeWork(firstTextView, 5)
                    firstTextView.text = "Count Done"
                }
                val job2 = launch {
                    doSomeWork(secondTextView, 10)
                    secondTextView.text = "Count Done"
                }
                job1.join()
                start.text = "End"
            }
        }
    }

    private suspend fun doSomeWork(textView: TextView, times: Int) {
        for (i in 0..times) {
            delay(500)
            textView.text = "Count:  $i"
        }
    }
}
