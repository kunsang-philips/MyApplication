package com.example.myapplication.coroutinetest

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class CoroutineTestViewModelTest {

    @Test
    fun testLikeCount1() = runBlocking {
        val coroutineTestViewModel = CoroutineTestViewModel()
        val likeCount = coroutineTestViewModel.getLikeCountFromDb()
        Assert.assertEquals(0, likeCount)
    }

    // the wrong way
    @Test
    fun testAddLikeCount() = runBlocking {
        val coroutineTestViewModel = CoroutineTestViewModel()
        coroutineTestViewModel.addLikeCount()
        Assert.assertEquals(1, coroutineTestViewModel.getLikeCountFromDb())
    }
}
