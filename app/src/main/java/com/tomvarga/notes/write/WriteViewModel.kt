package com.tomvarga.notes.write

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.tomvarga.notes.database.Note
import com.tomvarga.notes.database.NoteDao
import kotlinx.coroutines.*

class WriteViewModel(
    val database: NoteDao,
    application: Application): AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private suspend fun insert(note: Note){
        withContext(Dispatchers.IO) {
            database.insert(note)
        }
    }

    private suspend fun update(note: Note){
        withContext(Dispatchers.IO) {
            database.update(note)
        }
    }

    private suspend fun getAllNotesListData(): List<Note>{
        return withContext(Dispatchers.IO){
           database.getAllNotesList()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun saveNote(note: Note?) {
        Log.i("OnClick","viewModel.saveNote()")
        uiScope.launch {
            if (note != null) {
                insert(note)
            }
        }
    }

    fun getNotes(){
        uiScope.launch {
            Log.i("List of Notes", getAllNotesListData().toString())
        }
    }

}