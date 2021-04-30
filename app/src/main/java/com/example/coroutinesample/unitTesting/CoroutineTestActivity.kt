package com.example.myapplication.unitTesting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutinesample.R
import com.example.myapplication.unitTesting.ui.main.CoroutineTestFragment

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
