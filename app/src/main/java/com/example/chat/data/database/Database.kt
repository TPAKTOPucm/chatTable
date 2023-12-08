package com.example.chat.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chat.data.daos.CharacterDao
import com.example.chat.data.moodels.*

@Database(entities = [CharacterCoreInfo::class, Actor::class, Alias::class, Title::class], version = 1)
abstract class Database: RoomDatabase(){
    abstract fun characterDao(): CharacterDao
}