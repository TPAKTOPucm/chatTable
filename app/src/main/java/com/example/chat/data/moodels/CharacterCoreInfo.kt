package com.example.chat.data.moodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterCoreInfo (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String?,
    val culture: String?,
    val born: String?,
    val page: Int
)