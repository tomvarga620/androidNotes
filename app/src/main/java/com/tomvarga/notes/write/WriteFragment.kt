package com.tomvarga.notes.write

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import com.tomvarga.notes.R
import com.tomvarga.notes.database.Note
import com.tomvarga.notes.database.NoteDatabase
import com.tomvarga.notes.databinding.FragmentWriteBinding

class WriteFragment : Fragment() {

    private lateinit var binding: FragmentWriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // binding
        binding = FragmentWriteBinding.inflate(inflater)
        binding.editText.requestFocus()

        // showing keyboard on fragment create
        showSoftKeyboard(binding.editText)

        // application
        val application = requireNotNull(this.activity).application

        // noteDao
        val dataSource = NoteDatabase.getInstance(application).noteDao

        // viewModelFactory
        val myViewModelFactory = WriteViewModelFactory(dataSource,application)

        // creating our ViewModel through ViewModelProvider with our viewModelFactory
        val writeViewModel: WriteViewModel = ViewModelProvider(this,myViewModelFactory)
            .get(WriteViewModel::class.java)

        binding.viewModel = writeViewModel

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val editTextObj = binding.editText

        when(id){
            R.id.action_save -> {

                // vytvorenie noveho objektu s textom
                val newNote: Note = Note(noteText = editTextObj.text.toString())

                // insert do db room
                binding.viewModel?.saveNote(newNote)

                // clear edit textu
                clearUserInput(editTextObj)
            }

            R.id.action_clear -> {

                clearUserInput(editTextObj)
                binding.viewModel?.getNotes()
                //Toast.makeText(context,notesPrint,Toast.LENGTH_LONG).show()

            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            //imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
            imm.toggleSoftInput(0,0)
        }
    }

    fun clearUserInput(v: EditText){
        v.text.clear()
    }


}
