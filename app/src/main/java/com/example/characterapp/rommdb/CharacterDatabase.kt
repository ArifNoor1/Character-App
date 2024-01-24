package com.example.characterapp.rommdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.characterapp.model.Result

@Database(entities = [Result::class], version = 1)
@TypeConverters(CharacterTypeConverter::class)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDao() : CharacterDao

    companion object {
        @Volatile
        private var INSTANCE : CharacterDatabase? = null

        fun getDatabase(context: Context) : CharacterDatabase{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CharacterDatabase::class.java,
                        "character.db"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as CharacterDatabase
        }
    }
}