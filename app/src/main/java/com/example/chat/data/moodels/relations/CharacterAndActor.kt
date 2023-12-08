package com.example.chat.data.moodels.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.chat.data.moodels.*

data class CharacterAndActor(
    @Embedded val character: CharacterCoreInfo,
    @Relation(parentColumn = "id",
        entityColumn = "characterId") val actor: Actor
)