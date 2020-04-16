package com.tomvarga.notes.write

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
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

        when(id){
            R.id.action_save -> Toast.makeText(context,"Save",Toast.LENGTH_LONG).show()
            R.id.action_clear -> Toast.makeText(context,"Cleared",Toast.LENGTH_LONG).show()
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

}
