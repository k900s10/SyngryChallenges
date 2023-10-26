package com.example.syngrychallenge.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.syngrychallenge.databinding.DialogDeleteNoteBinding
import com.example.syngrychallenge.domain.model.NoteModel
import com.example.syngrychallenge.ui.viewModel.DeleteNoteViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeleteNoteDialog : DialogFragment() {
    private val binding get() = _binding!!
    private var _binding: DialogDeleteNoteBinding? = null
    private val viewModel: DeleteNoteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogDeleteNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCancel = binding.btnNoteCancel
        val btnDelete = binding.btnNoteDelete
        val bundle = arguments
        val noteModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle?.getParcelable(HomeFragment.ON_DELETE, NoteModel::class.java)
        } else {
            bundle?.getParcelable(HomeFragment.ON_DELETE)
        }

        btnCancel.setOnClickListener {
            dialog?.dismiss()
        }
        btnDelete.setOnClickListener {
            viewModel.deleteNote(noteModel!!)
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