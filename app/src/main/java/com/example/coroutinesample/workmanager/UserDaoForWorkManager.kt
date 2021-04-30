package com.example.coroutinesample.workmanager

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coroutinesample.room.User

@Dao
interface UserDaoForWorkManager {
    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

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
