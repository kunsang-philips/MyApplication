package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sequential.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SequentialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sequential)
        start.setOnClickListener {
            start.text = "Start"
            Log.d("FAFA", "Outside GlobalScope {${Thread.currentThread().name}}")

            GlobalScope.launch {
                Log.d("FAFA", "Inside GlobalScope {${Thread.currentThread().name}}")
                doSomeWork(firstTextView)
                doSomeWork(secondTextView)
                start.text = "End"
            }
        }
    }

    private suspend fun doSomeWork(textView: TextView) {
        Log.d("FAFA", "Inside doSomeWork {${Thread.currentThread().name}}")
        for (i in 0..10) {
            delay(500)
            withContext(Main) {
                textView.text = "Count:  $i"
            }
        }
    }
}