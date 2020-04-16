package com.tomvarga.notes.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class Database : RoomDatabase() {

    abstract val noteDatabase: NoteDao

    @Volatile
    private var INSTANCE: Database? = null

    fun getInstance(context: Context): Database{
        synchronized(this){
            var instance = INSTANCE

            if(instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "notes_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
            }
            return instance
        }
    }
}