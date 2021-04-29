package com.example.myapplication.coroutinetest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class CoroutineTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coroutine_test_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CoroutineTestFragment.newInstance())
                .commitNow()
        }
    }
}
