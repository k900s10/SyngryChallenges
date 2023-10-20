package com.example.syngrychallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.syngrychallenge.databinding.FragmentHomeBinding
import com.example.syngrychallenge.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding : FragmentRegisterBinding? = null
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

        navigateToLogin.setOnClickListener {
            // this is how to go back
            findNavController().popBackStack()
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}