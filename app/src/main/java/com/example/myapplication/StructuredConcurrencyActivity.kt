package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_global_scope.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class StructuredConcurrencyActivity : AppCompatActivity() {
    var parentJob: Job? = null
    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("FAFA", "Exception thrown $exception")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_structured_concurrency)
        button.setOnClickListener {
            textView.text = "Starting Job..."
            //startParentJob()
            startSupervisedJob()
        }
    }

    private fun startSupervisedJob() {
        parentJob = CoroutineScope(Main).launch(handler) {
            supervisorScope {
                // Job A
                val jobA = launch {
                    val result = doSomeWork(1)
                    Log.d("FAFA", "Result A $result")
                }
                jobA.invokeOnCompletion {
                    if (it != null) {
                        Log.e("FAFA", "error getting resultA: $it")
                    }
                }

                // Job B
                val jobB = launch {
                    val result = doSomeWork(2)
                    Log.d("FAFA", "Result B $result")
                }
                jobB.invokeOnCompletion {
                    if (it != null) {
                        Log.e("FAFA", "error getting resultB: $it")
                    }
                }

                // Job C
                val jobC = launch {
                    val result = doSomeWork(3)
                    Log.d("FAFA", "Result C $result")
                }
                jobC.invokeOnCompletion {
                    if (it != null) {
                        Log.e("FAFA", "error getting resultC: $it")
                    }
                }
            }

        }
        parentJob?.invokeOnCompletion {
            if (it != null) {
                textView.text = "Parent Job Failed"
                Log.e("FAFA", "Parent Job Failed")
            } else {
                textView.text = "Parent Job Successful"
                Log.e("FAFA", "Parent Job Successful")
            }
        }
    }

    private fun startParentJob() {
        parentJob = CoroutineScope(Main).launch(handler) {
            // Job A
            val jobA = launch {
                val result = doSomeWork(1)
                Log.d("FAFA", "Result A $result")
            }
            jobA.invokeOnCompletion {
                if (it != null) {
                    Log.e("FAFA", "error getting resultA: $it")
                }
            }

            // Job B
            val jobB = launch {
                val result = doSomeWork(2)
                Log.d("FAFA", "Result B $result")
            }
            jobB.invokeOnCompletion {
                if (it != null) {
                    Log.e("FAFA", "error getting resultB: $it")
                }
            }

            // Job C
            val jobC = launch {
                val result = doSomeWork(3)
                Log.d("FAFA", "Result C $result")
            }
            jobC.invokeOnCompletion {
                if (it != null) {
                    Log.e("FAFA", "error getting resultC: $it")
                }
            }
        }
        parentJob?.invokeOnCompletion {
            if (it != null) {
                textView.text = "Parent Job Failed"
                Log.e("FAFA", "Parent Job Failed")
            } else {
                textView.text = "Parent Job Successful"
                Log.d("FAFA", "Parent Job Successful")
            }
        }
    }

    private suspend fun doSomeWork(number: Int): Int {
        delay(number * 1000L)
        if (number == 2) {
            //throw Exception("Error in $number")
            throw CancellationException("Error in $number")
        }
        return number
    }
}