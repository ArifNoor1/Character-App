package com.example.characterapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "characterInfo")
data class Result(
    @PrimaryKey
    val id: String,
    val image: String?,
    val name: String?,
    val status: String?,
)