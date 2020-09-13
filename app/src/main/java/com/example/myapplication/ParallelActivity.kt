package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sequential.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main

class ParallelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parallel)
         //firstApproach()
        secondApproach()
    }

    private fun secondApproach() {
        start.setOnClickListener {
            start.text = "Start"
            firstTextView.text = "FirstTextView"
            secondTextView.text = "SecondTextView"

            GlobalScope.launch(Main) {
                val firstTask = async { doSomeWork(firstTextView) }
                val secondTask = async { doSomeWork(secondTextView) }
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
            GlobalScope.launch(Main) {
                launch(Default) {
                    doSomeWork(firstTextView)
                    withContext(Main) {
                        firstTextView.text = "Count Done"
                    }
                }
                launch(Default) {
                    doSomeWork(secondTextView)
                    withContext(Main) {
                        secondTextView.text = "Count Done"
                    }
                }
                start.text = "End"
            }
        }
    }

    private suspend fun doSomeWork(textView: TextView) {
        for (i in 0..10) {
            delay(500)
            withContext(Main) {
                textView.text = "Count:  $i"
            }
        }
    }
}