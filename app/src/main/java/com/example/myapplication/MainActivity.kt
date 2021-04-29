package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.basic.BasicsActivity
import com.example.myapplication.flows.FlowActivity
import com.example.myapplication.flows.FlowRealTimeActivity
import com.example.myapplication.livedata.LiveDataActivity
import com.example.myapplication.parallel.ParallelActivity
import com.example.myapplication.room.RoomDBActivity
import com.example.myapplication.scopes.GlobalScopeActivity
import com.example.myapplication.scopes.ScopeActivity
import com.example.myapplication.sequential.SequentialActivity
import com.example.myapplication.structured.StructuredConcurrencyActivity
import com.example.myapplication.workmanager.WorkManagerActivity
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
            startActivity(Intent(this, WorkManagerActivity::class.java))
        }
    }
}
