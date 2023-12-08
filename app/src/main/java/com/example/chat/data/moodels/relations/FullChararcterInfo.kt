package com.example.chat.data.moodels.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.chat.data.moodels.*

data class FullChararcterInfo(
    @Embedded val character: CharacterCoreInfo,
    @Relation(parentColumn = "id",
        entityColumn = "characterId") val aliases: List<Alias>,
    @Relation(parentColumn = "id",
        entityColumn = "characterId") val titles: List<Title>,
    @Relation(parentColumn = "id",
        entityColumn = "characterId") val actors: List<Actor>
)