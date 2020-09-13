package com.example.myapplication

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_call_back.*


class CallBackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_back)
        button.setOnClickListener {
            textMessage.text = "Start"
            val someImplClass = SomeImplClass()
            someImplClass.doLongRunningTask(object : LongRunningTask {
                override fun onLongRunningTask(someMessage: String) {
                    runOnUiThread {
                        textMessage.text = someMessage
                    }
                    someImplClass.doAnotherLongRunningTaskAfter(object : LongRunningTask {
                        override fun onLongRunningTask(someMessage: String) {
                            runOnUiThread {
                                textMessage.text = someMessage
                            }
                        }
                    })
                }
            })
        }
    }
}

class SomeImplClass {
    fun doLongRunningTask(longRunningTask: LongRunningTask) {
        AsyncTask.execute {
            Thread.sleep(3000)
            longRunningTask.onLongRunningTask("doLongRunningTask is done")
        }
    }

    fun doAnotherLongRunningTaskAfter(longRunningTask: LongRunningTask) {
        Thread.sleep(3000)
        longRunningTask.onLongRunningTask("doAnotherLongRunningTaskAfter is done")
    }
}

interface LongRunningTask {
    fun onLongRunningTask(someMessage: String)
}