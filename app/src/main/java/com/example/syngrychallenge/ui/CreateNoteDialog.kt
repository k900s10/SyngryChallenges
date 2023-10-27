package com.example.syngrychallenge.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.syngrychallenge.R
import com.example.syngrychallenge.databinding.DialogCreateNoteBinding
import com.example.syngrychallenge.domain.model.NoteModel
import com.example.syngrychallenge.ui.viewModel.CreateNoteViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateNoteDialog : DialogFragment() {
    private val binding get() = _binding!!
    private var _binding: DialogCreateNoteBinding? = null
    private val viewModel: CreateNoteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogCreateNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSubmit = binding.btnCreateNote
        val bundle = arguments
        val username = bundle?.getString(HomeFragment.ON_CREATE)

        btnSubmit.setOnClickListener {
            val inputTitle = binding.etCreateNoteTitle.text.toString()
            val inputNote = binding.etCreateNoteNote.text.toString()

            if (username != null) {
                viewModel.createNote(username = username, title = inputTitle, note = inputNote)
                runBlocking {
                    delay(100)
                    dialog?.dismiss()
                }
            } else {
                Toast.makeText(activity, getString(R.string.create_note_failed), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}