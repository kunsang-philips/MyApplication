package com.example.myapplication

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_basics.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BasicsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basics)
        firstConcept()
        //secondConcept()
    }

    private fun firstConcept() {
        button.setOnClickListener {
            textView.text = ""
            callNormalFunction("Welcome ")
            GlobalScope.launch(Main) {
                // launch a new coroutine in background and continue
                // callNormalFunction("World ")

                Thread.sleep(1000)
                Log.d("FAFA", "Kunsang ${Thread.currentThread().name} ${Thread.currentThread().id}")
                runOnUiThread {
                    textView.append("World")
                }
            }
            callNormalFunction("to ")
            callNormalFunction("Android ")
        }
    }

    private fun callNormalFunction(string: String) {
        AsyncTask.execute {
            Thread.sleep(1000)
            Log.d("FAFA", "${Thread.currentThread().name} ${Thread.currentThread().id}")
            runOnUiThread {
                textView.append(string)
            }
        }
    }

    private fun secondConcept() {
        button.setOnClickListener {
            GlobalScope.launch {
                var message = doLongRunningTask()
                withContext(Main) {
                    textView.text = message
                }
                message = doAnotherLongRunningTaskAfter()
                withContext(Main) {
                    textView.text = message
                }
            }
        }
    }

    suspend fun doLongRunningTask(): String {
        delay(3000)
        return "doLongRunningTask is done"
    }

    suspend fun doAnotherLongRunningTaskAfter(): String {
        delay(3000)
        return "doAnotherLongRunningTaskAfter"
    }
}
