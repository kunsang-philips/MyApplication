package com.example.coroutinesample.sequential

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.coroutinesample.R
import kotlinx.android.synthetic.main.activity_sequential.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SequentialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sequential)
        start.setOnClickListener {
            start.text = "Start"
            lifecycleScope.launch {
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
            textView.text = "Count:  $i"
        }
    }
}
