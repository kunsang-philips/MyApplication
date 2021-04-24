package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_scope_second.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScopeSecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scope_second)
        // firstApproach()
        secondApproach()
    }

    private fun secondApproach() {
        start.setOnClickListener {
            lifecycleScope.launch {
                doSomeLongRunningTaskWithScope()
            }
        }
    }

    private fun firstApproach() {
        start.setOnClickListener {
            GlobalScope.launch(IO) {
                doSomeLongRunningTask()
            }
        }
    }

    private suspend fun doSomeLongRunningTask() {
        withContext(Main) {
            Toast.makeText(this@ScopeSecondActivity, "Work Started", Toast.LENGTH_SHORT).show()
            delay(5000L)
            Toast.makeText(this@ScopeSecondActivity, "Work Ended", Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun doSomeLongRunningTaskWithScope() {
        Toast.makeText(this, "Work Started", Toast.LENGTH_SHORT).show()
        delay(5000L)
        Toast.makeText(this, "Work Ended", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
