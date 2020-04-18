package com.tomvarga.notes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "notes_database")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val noteId: Long = 0L,

    @NotNull
    @ColumnInfo(name = "note")
    val noteText: String?
)