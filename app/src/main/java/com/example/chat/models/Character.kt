package com.example.chat.models

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val name: String?,
    val culture: String?,
    val born: String?,
    val titles: Iterable<String>,
    val aliases: Iterable<String>,
    val playedBy: Iterable<String>
)