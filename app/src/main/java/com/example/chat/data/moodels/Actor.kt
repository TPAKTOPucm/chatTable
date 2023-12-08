package com.example.chat.data.moodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Actor(
    @PrimaryKey(true) val id: Int,
    val name: String,
    val characterId: Int
)