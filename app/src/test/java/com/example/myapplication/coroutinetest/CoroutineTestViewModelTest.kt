package com.example.myapplication.coroutinetest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CoroutineTestViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        // Sets the given [dispatcher] as an underlying dispatcher of [Dispatchers.Main].
        // All consecutive usages of [Dispatchers.Main] will use given [dispatcher] under the hood.
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        // Resets state of the [Dispatchers.Main] to the original main dispatcher.
        // For example, in Android Main thread dispatcher will be set as [Dispatchers.Main].
        Dispatchers.resetMain()

        // Clean up the TestCoroutineDispatcher to make sure no other work is running.
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testLikeCount1() = runBlocking {
        val coroutineTestViewModel = CoroutineTestViewModel(testDispatcher)
        val likeCount = coroutineTestViewModel.getLikeCount()
        Assert.assertEquals(0, likeCount)
    }

    @Test
    fun testAddLikeCount() = runBlocking {
        val coroutineTestViewModel = CoroutineTestViewModel(testDispatcher)
        coroutineTestViewModel.addLikeCount()
        Assert.assertEquals(1, coroutineTestViewModel.getLikeCount())
    }
}
