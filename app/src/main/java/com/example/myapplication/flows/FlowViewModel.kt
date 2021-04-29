package com.example.myapplication.flows

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlowViewModel : ViewModel() {
    var flowTimer: Flow<String>? = null

    fun sendOtp() {
        flowTimer = flow {
            for (i in 1..60) {
                emit("" + i)
                delay(1000)
            }
            emit("Resend OTP")
        }
    }
}
