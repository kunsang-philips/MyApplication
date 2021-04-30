package com.example.myapplication.coroutinetest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineTestViewModel(private val dispatcher: CoroutineDispatcher) : ViewModel() {
    private var likeCount = 0
    private val _likeCountLiveData = MutableLiveData("")
    val likeCountLiveData: LiveData<String>
        get() = _likeCountLiveData

    fun getLikeCount() = likeCount

    fun addLikeCount() {
        viewModelScope.launch(dispatcher) {
            likeCount = getLikeCountFromDb() + 1
            _likeCountLiveData.postValue(likeCount.toString())
        }
    }

    suspend fun getLikeCountFromDb(): Int =
        withContext(dispatcher) {
            delay(10_000) // Assume we get data from database
            likeCount
        }
}
