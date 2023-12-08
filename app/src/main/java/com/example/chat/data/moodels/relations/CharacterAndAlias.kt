package com.example.chat.data.moodels.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.chat.data.moodels.*

data class CharacterAndAlias(
    @Embedded val character: CharacterCoreInfo,
    @Relation(parentColumn = "id",
        entityColumn = "characterId") val allias: Alias
)