package com.example.myapplication.coroutinetest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineTestViewModel : ViewModel() {
    var likeCount = 0
    fun addLikeCount() {
        viewModelScope.launch {
            likeCount = getLikeCountFromDb() + 1
        }
    }

    suspend fun getLikeCountFromDb(): Int =
        withContext(IO) {
            delay(10_000) // Assume we get data from database
            likeCount
        }
}
