package com.example.syngrychallenge.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.syngrychallenge.R
import com.example.syngrychallenge.databinding.FragmentProfileBinding
import com.example.syngrychallenge.ui.viewModel.ProfileViewModel
import com.example.syngrychallenge.utils.Util.safeNavigate
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding? = null
    private val binding get() =   _binding!!
    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnUpdateProfile = binding.buttonUpdateProfile
        val btnLogout = binding.buttonLogout

        viewModel.getProfile().observe(viewLifecycleOwner) {profile ->

            binding.etUsername.setText(profile.username)
            binding.etName.setText(profile.name)
            binding.etBirthDate.setText(profile.birthday)
            binding.etAddress.setText(profile.address)
        }

        btnLogout.setOnClickListener {
            viewModel.logout()
            val destination = ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
            findNavController().safeNavigate(destination)
        }

        btnUpdateProfile.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val name = binding.etName.text.toString()
            val birthday = binding.etBirthDate.text.toString()
            val address = binding.etAddress.text.toString()

            viewModel.updateProfile(username = username, name = name, birthday = birthday, address = address)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}