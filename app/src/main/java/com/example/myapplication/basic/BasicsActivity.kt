package com.example.myapplication.basic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_basics.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BasicsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basics)
        showMessageOneByOne()
    }

    private fun showMessageOneByOne() {
        button.setOnClickListener {
            lifecycleScope.launch {
                textView.text = ""
                textView.append(doLongRunningTask())
                textView.append(doAnotherLongRunningTaskAfter())
            }
        }
    }

    suspend fun doLongRunningTask(): String {
        delay(3000)
        return "doLongRunningTask is done\n"
    }

    suspend fun doAnotherLongRunningTaskAfter(): String {
        delay(3000)
        return "doAnotherLongRunningTaskAfter"
    }
}
