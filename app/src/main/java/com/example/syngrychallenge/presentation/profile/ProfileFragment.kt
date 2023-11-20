package com.example.syngrychallenge.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.syngrychallenge.R
import com.example.core.data.local.pref.result.DataStoreResult
import com.example.syngrychallenge.databinding.FragmentProfileBinding
import com.example.syngrychallenge.utils.AppUtils.safeNavigate
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
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
        val progressBar = binding.progressBar

        viewModel.getProfile().observe(viewLifecycleOwner) { profile ->

            binding.etUsername.setText(profile.username)
            binding.etName.setText(profile.name)
            binding.etBirthDate.setText(profile.birthday)
            binding.etAddress.setText(profile.address)
        }

        btnLogout.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            viewModel.logout().observe(viewLifecycleOwner) { result ->
                when (result) {
                    is DataStoreResult.Success -> {
                        progressBar.visibility = View.GONE
                        val destination =
                            ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
                        findNavController().safeNavigate(destination)
                    }

                    is DataStoreResult.Error -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(activity, R.string.logout_failed, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        btnUpdateProfile.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val name = binding.etName.text.toString()
            val birthday = binding.etBirthDate.text.toString()
            val address = binding.etAddress.text.toString()

            progressBar.visibility = View.VISIBLE
            viewModel.updateProfile(
                username = username,
                name = name,
                birthday = birthday,
                address = address
            ).observe(viewLifecycleOwner) { result ->
                when (result) {
                    is DataStoreResult.Success -> {
                        progressBar.visibility = View.GONE
                    }

                    is DataStoreResult.Error -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(activity, R.string.update_profile_failed, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}