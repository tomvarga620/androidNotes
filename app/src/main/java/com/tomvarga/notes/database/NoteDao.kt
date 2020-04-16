package com.tomvarga.notes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert
    fun insert(note:Note)

    @Update
    fun update(note:Note)

    @Query("SELECT * FROM notes_database ORDER BY noteId ")
    fun getAllNotes()

}