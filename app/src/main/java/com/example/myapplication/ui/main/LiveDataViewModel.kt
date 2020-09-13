package com.example.myapplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class LiveDataViewModel : ViewModel() {
    private val dataString = MutableLiveData("Android Rocks!! ")
    fun load() {
        dataString.postValue(dataString.value.plus("B. "))
    }

    val liveDataString = liveData {
        emit(dataString.value)
        emitSource(dataString)
    }
}