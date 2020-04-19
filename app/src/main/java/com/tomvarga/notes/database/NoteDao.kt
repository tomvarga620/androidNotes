package com.tomvarga.notes.database

import androidx.lifecycle.LiveData
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
    fun getAllNotesList(): List<Note>

    @Query("SELECT * FROM notes_database ORDER BY noteId ")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * from notes_database WHERE noteId = :key")
    fun get(key: Long): Note?

    @Query("DELETE FROM notes_database")
    fun deleteAllRecords()

}