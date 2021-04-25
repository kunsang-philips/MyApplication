package com.example.myapplication.structured

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_global_scope.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import java.io.InvalidClassException

class StructuredConcurrencyActivity : AppCompatActivity() {
    var parentJob: Job? = null
    private val handler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is InvalidClassException -> {
                Toast.makeText(this, "InvalidClassException", Toast.LENGTH_SHORT).show()
            }
            is OutOfMemoryError -> {
                Toast.makeText(this, "OutOfMemoryError", Toast.LENGTH_SHORT).show()
            }
            is java.lang.NumberFormatException -> {
                Toast.makeText(this, "NumberFormatException", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "Other Exception", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_structured_concurrency)
        button.setOnClickListener {
            textView.text = "Starting Job..."
            // startParentJob()
            startSupervisedJob()
        }
    }

    private fun startSupervisedJob() {
        parentJob = CoroutineScope(Main).launch(handler) {
            supervisorScope {
                // Job A 
                launch {
                    val result = doSomeWork1()
                    Log.d("FAFA", "Result A $result")
                }

                // Job B
                launch {
                    val result = doSomeWork2()
                    Log.d("FAFA", "Result B $result")
                }
                // Job B
                launch {
                    val result = doSomeWork3()
                    Log.d("FAFA", "Result C $result")
                }
                doSomeWork4()
            }
        }
    }

    private suspend fun doSomeWork1(): Int {
        delay(1 * 1000L)
        throw InvalidClassException("Error in 1")
    }

    private suspend fun doSomeWork2(): Int {
        delay(2 * 1000L)
        throw OutOfMemoryError("Error in 2")
    }

    private suspend fun doSomeWork3(): Int {
        delay(3 * 1000L)
        throw NumberFormatException("Error in 3")
    }

    private suspend fun doSomeWork4(): Int {
        delay(4 * 1000L)
        throw Exception("Error in Parent")
    }
}
