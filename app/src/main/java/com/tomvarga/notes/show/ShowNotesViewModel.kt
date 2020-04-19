package com.tomvarga.notes.show

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.tomvarga.notes.database.NoteDao

class ShowNotesViewModel(
    val database: NoteDao,
    application: Application
) : AndroidViewModel(application) {

}
