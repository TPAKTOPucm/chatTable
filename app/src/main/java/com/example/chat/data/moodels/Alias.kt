package com.example.chat.data.moodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Alias(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val characterId: Int
)