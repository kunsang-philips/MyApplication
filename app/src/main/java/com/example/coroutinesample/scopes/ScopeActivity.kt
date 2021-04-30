package com.example.coroutinesample.scopes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutinesample.R
import kotlinx.android.synthetic.main.activity_scope.*

class ScopeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scope)
        gotoSecondActivity.setOnClickListener {
            startActivity(Intent(this, ScopeSecondActivity::class.java))
        }
    }
}