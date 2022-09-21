package com.mirkamol.delegation.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val description: String,
    val image: String
)