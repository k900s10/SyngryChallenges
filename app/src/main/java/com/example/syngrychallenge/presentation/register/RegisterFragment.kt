package com.example.syngrychallenge.presentation.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.syngrychallenge.R
import com.example.syngrychallenge.data.local.pref.result.DataStoreResult
import com.example.syngrychallenge.databinding.FragmentRegisterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentRegisterBinding? = null
    private val viewModel: RegisterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navigateToLogin = binding.tvAlreadyHaveAccount
        val btnRegister = binding.buttonRegister

        navigateToLogin.setOnClickListener {
            // this is how to go back
            findNavController().popBackStack()
        }

        btnRegister.setOnClickListener {
            val progressBar = binding.progressBar
            val inputUsername = binding.etRegisterUsername.text.toString()
            val inputEmail = binding.etRegisterEmail.text.toString()
            val inputPassword = binding.etRegisterPassword.text.toString()
            val inputConfirmPassword = binding.etRegisterConfirmPassword.text.toString()

            progressBar.visibility = View.VISIBLE
            viewModel.createAccount(
                username = inputUsername,
                email = inputEmail,
                password = inputPassword,
                confirmPassword = inputConfirmPassword
            ).observe(viewLifecycleOwner) {result ->
                when (result) {
                    is DataStoreResult.Success -> {
                        progressBar.visibility = View.GONE
                        findNavController().popBackStack()
                    }
                    is DataStoreResult.Error -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(activity, R.string.register_failed, Toast.LENGTH_SHORT).show()
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