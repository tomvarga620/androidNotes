package com.tomvarga.notes.write

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider

import com.tomvarga.notes.R
import com.tomvarga.notes.databinding.FragmentWriteBinding
import kotlinx.android.synthetic.main.fragment_write.*

class WriteFragment : Fragment() {

    private lateinit var viewModel: WriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentWriteBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(WriteViewModel::class.java)
        binding.viewModel = viewModel
        binding.editText.requestFocus()

        showSoftKeyboard(binding.editText)

        return binding.root
    }

    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            //imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
            imm.toggleSoftInput(0,0)
        }
    }

}
