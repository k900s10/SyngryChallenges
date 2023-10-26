package com.example.syngrychallenge.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import com.example.syngrychallenge.R
import com.example.syngrychallenge.databinding.FragmentHomeBinding
import com.example.syngrychallenge.domain.model.NoteModel
import com.example.syngrychallenge.ui.adapter.HomeAdapter
import com.example.syngrychallenge.ui.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logout = binding.tvLogout
        val _adapter = HomeAdapter()
        val emptyList = binding.tvEmptyRecyclerView
        val svRecyclerView = binding.svRecyclerView
        val fabCreateNote = binding.fabAddNote
        val username = HomeFragmentArgs.fromBundle(arguments as Bundle).username

        binding.tvUsername.text = getString(R.string.tv_home_username, username)
        viewModel.getAllNotes.observe(viewLifecycleOwner) { notes ->
            if (notes.isEmpty()) {
                emptyList.visibility = View.VISIBLE
                svRecyclerView.visibility = View.GONE
            } else {
                _adapter.submitList(notes)
                emptyList.visibility = View.GONE
                svRecyclerView.visibility = View.VISIBLE
            }
        }
        binding.recyclerView.apply {
            adapter = _adapter
        }

        _adapter.setOnDeleteClickCallback(object : HomeAdapter.OnDeletelickCallback {
            override fun onItemClicked(note: NoteModel, context: Context) {
                val fragmentManager = childFragmentManager
                val dialog = DeleteNoteDialog()
                val bundle = Bundle()

                bundle.putParcelable(ON_DELETE, note)
                dialog.arguments = bundle
                dialog.show(fragmentManager, "deleteNoteDialog")
            }
        })
        _adapter.setOnUpdateClickCallback(object : HomeAdapter.OnUpdatelickCallback {
            override fun onItemClicked(note: NoteModel, context: Context) {
                val fragmentManager = childFragmentManager
                val dialog = EditNoteDialog()
                val bundle = Bundle()

                bundle.putParcelable(ON_UPDATE, note)
                dialog.arguments = bundle
                dialog.show(fragmentManager, "editNoteDialog")
            }
        })

        logout.setOnClickListener {
            // this is how to go back
            findNavController().popBackStack()
        }

        fabCreateNote.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val fragmentManager = childFragmentManager
        val newFragment = CreateNoteDialog()
        newFragment.show(fragmentManager, "dialog")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ON_DELETE = "DELETE NOTE"
        const val ON_UPDATE = "UPDATE NOTE"
    }
}