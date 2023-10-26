package com.example.syngrychallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.syngrychallenge.databinding.DialogCreateNoteBinding
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

        btnSubmit.setOnClickListener {
            val inputTitle = binding.etCreateNoteTitle.text.toString()
            val inputNote = binding.etCreateNoteNote.text.toString()

            viewModel.createNote(inputTitle, inputNote)
            runBlocking {
                delay(100)
                dialog?.dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}