package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_flow.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FlowActivity : AppCompatActivity() {

    // https://www.youtube.com/watch?v=CIvjwIfOG5A&t=11s
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)
        button.setOnClickListener {
            lifecycleScope.launch {
                sendNumber().collect {
                    textView.append("$it ")
                }
            }
        }
    }

    // Flow cannot be cancelled. u have to cancel parent job
    private suspend fun sendNumber(): Flow<Int> {
        return flow {
            val parameter = listOf(1, 2, 3, 4, 5)
            parameter.forEach {
                delay(500L)
                emit(it)
            }
        }
    }
}