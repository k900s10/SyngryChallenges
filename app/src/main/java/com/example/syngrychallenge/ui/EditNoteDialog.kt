package com.example.syngrychallenge.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.syngrychallenge.databinding.DialogEditNoteBinding
import com.example.syngrychallenge.domain.model.NoteModel
import com.example.syngrychallenge.ui.viewModel.EditNoteViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditNoteDialog : DialogFragment() {
    private val binding get() = _binding!!
    private var _binding: DialogEditNoteBinding? = null
    private val viewModel: EditNoteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogEditNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSubmit = binding.btnEditNote
        val bundle = arguments
        val noteModel  = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle?.getParcelable(HomeFragment.ON_UPDATE, NoteModel::class.java)
        } else {
            bundle?.getParcelable(HomeFragment.ON_UPDATE)
        }
        val inputTitle = binding.etEditNoteTitle
        val inputNote = binding.etEditNoteNote

        inputTitle.setText(noteModel?.title)
        inputNote.setText(noteModel?.note)

        btnSubmit.setOnClickListener {
            val inputTitleText = binding.etEditNoteTitle.text.toString()
            val inputNoteText = binding.etEditNoteNote.text.toString()
            val noteId = noteModel!!.id
            viewModel.updateNote(noteId, inputTitleText, inputNoteText)
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