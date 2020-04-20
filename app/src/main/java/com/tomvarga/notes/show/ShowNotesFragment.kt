package com.tomvarga.notes.show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.tomvarga.notes.R
import com.tomvarga.notes.database.NoteDatabase
import com.tomvarga.notes.databinding.ShowNotesFragmentBinding

class ShowNotesFragment : Fragment() {

    private lateinit var binding: ShowNotesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ShowNotesFragmentBinding.inflate(inflater)

        // application
        val application = requireNotNull(this.activity).application

        // noteDao
        val dataSource = NoteDatabase.getInstance(application).noteDao

        // viewModelFactory
        val myViewModelFactory = ShowNotesViewFactory(dataSource,application)

        // creating our ViewModel through ViewModelProvider with our viewModelFactory
        val showNotesViewModel = ViewModelProvider(this,myViewModelFactory)
            .get(ShowNotesViewModel::class.java)

        val adapter = ShowNotesAdapter()

        binding.viewModel = showNotesViewModel
        binding.notesList.adapter = adapter

        showNotesViewModel.notes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        val manager = LinearLayoutManager(activity)
        manager.orientation = RecyclerView.VERTICAL
        binding.notesList.layoutManager = manager

        //binding.setLifecycleOwner(this)

        return binding.root
    }

}
