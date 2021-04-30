package com.example.coroutinesample.flows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutinesample.R

class FlowRealTimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.flow_real_time_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FlowRealTimeFragment.newInstance())
                .commitNow()
        }
    }
}