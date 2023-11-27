package com.example.syngrychallenge.presentation.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.core.utils.result.AuthResult
import com.example.syngrychallenge.R
import com.example.syngrychallenge.databinding.FragmentLoginBinding
import com.example.syngrychallenge.utils.AppUtils.safeNavigate
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentLoginBinding? = null
    private val viewModel: LoginViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val register = binding.tvCreateAccount
        val btnLogin = binding.btnLogin

        viewModel.isLogin.observe(viewLifecycleOwner) { isLogin ->
            Log.d("fragment", isLogin.toString()) //magic. its working when i add it. im tired.
            if (isLogin) {
                val destination =
                    LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                findNavController().safeNavigate(destination)
            }
        }

        register.setOnClickListener {
            val destination =
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().safeNavigate(destination)
        }

        btnLogin.setOnClickListener {
            val inputEmail = binding.etLoginEmail.text.toString()
            val inputPassword = binding.etLoginPassword.text.toString()
            val progressBar = binding.progressBar

            progressBar.visibility = View.VISIBLE
            viewModel.auth(inputEmail, inputPassword).observe(viewLifecycleOwner) { auth ->
                when (auth) {
                    AuthResult.Success -> {
                        progressBar.visibility = View.GONE
                        val destination =
                            LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                        findNavController().safeNavigate(destination)
                    }

                    AuthResult.Failed -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(
                            activity,
                            getString(R.string.login_failed),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            }
//            viewModel.validation.observe(viewLifecycleOwner) { validation ->
//                when (validation) {
//                    true -> {
//                        viewModel.createLoginSession.observe(viewLifecycleOwner) { result ->
//                            when (result) {
//                                is DataStoreResult.Success -> {
//                                    progressBar.visibility = View.GONE
//                                    val destination =
//                                        LoginFragmentDirections.actionLoginFragmentToHomeFragment()
//                                    findNavController().safeNavigate(destination)
//                                }
//
//                                is DataStoreResult.Error -> {
//                                    progressBar.visibility = View.GONE
//                                    Toast.makeText(
//                                        activity,
//                                        R.string.login_failed,
//                                        Toast.LENGTH_LONG
//                                    ).show()
//                                }
//                            }
//                        }
//                    }
//
//                    false -> {
//                        progressBar.visibility = View.GONE
//                        Toast.makeText(
//                            activity,
//                            getString(R.string.login_failed),
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//
//                }
//            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}