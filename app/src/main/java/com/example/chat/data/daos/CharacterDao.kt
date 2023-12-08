package com.example.chat.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.chat.data.moodels.*
import com.example.chat.data.moodels.relations.FullChararcterInfo

@Dao
interface CharacterDao {
    @Transaction
    @Query("SELECT * FROM CharacterCoreInfo WHERE page = :page")
    suspend fun getCharacters(page: Int): List<FullChararcterInfo>

    @Query("SELECT id FROM CharacterCoreInfo ORDER BY id DESC LIMIT 1")
    suspend fun getLastCharacterId(): Int

    @Insert
    suspend fun insertCharacterCoreInfo(coreInfo: CharacterCoreInfo)

    @Insert
    suspend fun insertActors(actors: List<Actor>)

    @Insert
    suspend fun insertAliases(aliases: List<Alias>)

    @Insert
    suspend fun insertTitles(titles: List<Title>)
}