package com.example.characterapp.rommdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.characterapp.model.Result

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(character: Result)

    @Delete
    suspend fun delete(character: Result)

    @Query("SELECT * FROM characterInfo")
    fun getAllCharacters() : LiveData<List<Result>>
}