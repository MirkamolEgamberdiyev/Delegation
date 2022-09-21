package com.mirkamol.delegation.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mirkamol.delegation.local.dao.UserDao
import com.mirkamol.delegation.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}