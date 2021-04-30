package com.example.coroutinesample.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDaoForRoom {
    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Query(
        "SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1"
    )
    suspend fun findByName(first: String, last: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}
