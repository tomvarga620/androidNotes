package com.tomvarga.notes.show

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tomvarga.notes.database.NoteDao
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ShowNotesViewFactory(
    private val data: NoteDao,
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(ShowNotesViewModel::class.java)){
                return ShowNotesViewModel(data,application) as T
            }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}