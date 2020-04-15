package com.tomvarga.notes.show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.tomvarga.notes.R
import com.tomvarga.notes.databinding.ShowNotesFragmentBinding

class ShowNotesFragment : Fragment() {

    private lateinit var viewModel: ShowNotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = ShowNotesFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(ShowNotesViewModel::class.java)

        binding.viewModel = viewModel

        return binding.root
    }

}
