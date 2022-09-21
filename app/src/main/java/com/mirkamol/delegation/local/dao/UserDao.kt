package com.mirkamol.delegation.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mirkamol.delegation.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFood(user: User)

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<User>>
}