package com.example.coroutinesample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutinesample.basic.BasicsActivity
import com.example.coroutinesample.flows.FlowActivity
import com.example.coroutinesample.flows.FlowRealTimeActivity
import com.example.coroutinesample.livedata.LiveDataActivity
import com.example.coroutinesample.parallel.ParallelActivity
import com.example.coroutinesample.room.RoomDBActivity
import com.example.coroutinesample.scopes.GlobalScopeActivity
import com.example.coroutinesample.scopes.ScopeActivity
import com.example.coroutinesample.sequential.SequentialActivity
import com.example.coroutinesample.structured.StructuredConcurrencyActivity
import com.example.coroutinesample.workmanager.WorkmanagerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callBack.setOnClickListener {
            startActivity(Intent(this, CallBackActivity::class.java))
        }

        basic.setOnClickListener {
            startActivity(Intent(this, BasicsActivity::class.java))
        }

        sequential.setOnClickListener {
            startActivity(Intent(this, SequentialActivity::class.java))
        }
        parallel.setOnClickListener {
            startActivity(Intent(this, ParallelActivity::class.java))
        }

        scope.setOnClickListener {
            startActivity(Intent(this, ScopeActivity::class.java))
        }

        globalScope.setOnClickListener {
            startActivity(Intent(this, GlobalScopeActivity::class.java))
        }

        concurrency.setOnClickListener {
            startActivity(Intent(this, StructuredConcurrencyActivity::class.java))
        }

        liveData.setOnClickListener {
            startActivity(Intent(this, LiveDataActivity::class.java))
        }
        flow.setOnClickListener {
            startActivity(Intent(this, FlowActivity::class.java))
        }
        flow1.setOnClickListener {
            startActivity(Intent(this, FlowRealTimeActivity::class.java))
        }
        roomDB.setOnClickListener {
            startActivity(Intent(this, RoomDBActivity::class.java))
        }
        workManager.setOnClickListener {
            startActivity(Intent(this, WorkmanagerActivity::class.java))
        }
    }
}
