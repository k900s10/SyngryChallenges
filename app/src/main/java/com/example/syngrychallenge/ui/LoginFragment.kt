package com.example.syngrychallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.syngrychallenge.R
import com.example.syngrychallenge.databinding.FragmentLoginBinding
import com.example.syngrychallenge.ui.viewModel.LoginViewModel
import com.example.syngrychallenge.utils.Util.safeNavigate
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
        if (viewModel.isLogin) {
            val destination =
                LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            findNavController().safeNavigate(destination)
        }

        register.setOnClickListener {
            val destination = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().safeNavigate(destination)
        }

        btnLogin.setOnClickListener {
            val inputEmail = binding.etLoginEmail.text.toString()
            val inputPassword = binding.etLoginPassword.text.toString()

            viewModel.auth(inputEmail, inputPassword)
            viewModel.validation.observe(viewLifecycleOwner) { validation ->
                when (validation) {
                    true -> {
                        viewModel.createLoginSession()
                        val destination =
                            LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                        findNavController().safeNavigate(destination)
                    }

                    false -> {
                        Toast.makeText(
                            activity,
                            getString(R.string.login_failed),
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //miracle
//    fun NavController.safeNavigate(direction: NavDirections) {
//        currentDestination?.getAction(direction.actionId)?.run {
//            navigate(direction)
//        }
//    }
}