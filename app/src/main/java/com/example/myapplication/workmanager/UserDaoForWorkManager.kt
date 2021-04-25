package com.example.myapplication.workmanager

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.room.User

@Dao
interface UserDaoForWorkManager {
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    @Query(
        "SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1"
    )
    suspend fun findByName(first: String, last: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}
