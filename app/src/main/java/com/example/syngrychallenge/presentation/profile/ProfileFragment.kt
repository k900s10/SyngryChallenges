package com.example.syngrychallenge.presentation.profile

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.core.data.PhotoProfileResult
import com.example.core.data.local.pref.result.DataStoreResult
import com.example.syngrychallenge.R
import com.example.syngrychallenge.databinding.FragmentProfileBinding
import com.example.syngrychallenge.utils.AppUtils.safeNavigate
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModel()
    private var photoProfilePath: String = ""

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
            photoProfilePath = profile.photoProfilePath.toString()
            if (profile.photoProfilePath != "null" &&
                profile.photoProfilePath?.isNotEmpty() == true) {
                Glide.with(requireActivity())
                    .load(profile.photoProfilePath)
                    .into(binding.ivProfilePicture)
            }
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
                address = address,
                photoProfilePath = photoProfilePath
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

        binding.ivProfilePicture.setOnClickListener {
            pickAPhoto()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun pickAPhoto() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) {
        try {
            val uri: Uri = it ?: throw Exception(getString(R.string.missing_image))

            viewModel.imageToBitmap(uri).observe(viewLifecycleOwner) { result ->
                when (result) {
                    is PhotoProfileResult.Success -> {
                        if (result.data.isNotEmpty()) {
                            //preview
                            Glide
                                .with(requireActivity())
                                .load(uri)
                                .into(binding.ivProfilePicture)
                            photoProfilePath = uri.toString()
                        }
                    }

                    is PhotoProfileResult.Error -> {
                        Toast.makeText(activity, R.string.missing_image, Toast.LENGTH_LONG).show()
                    }
                }
            }
//            viewModel.imageToBitmap(uri).observe(viewLifecycleOwner) { result ->
//                when (result) {
//                    is SaveImageResult.Success -> {
//                        val imagePath = result.data
//                        Glide
//                            .with(requireActivity())
//                            .load(imagePath)
//                            .error(R.drawable.ic_error)
//                            .into(binding.ivProfilePicture)
//                    }
//
//                    is SaveImageResult.Error -> {
//                        Toast.makeText(
//                            activity,
//                            getString(R.string.missing_image),
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//                }
//            }
        } catch (e: Exception) {
            Log.w("imagePickerIntent", e.toString())
            Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
        }
    }


//    private val launcherGallery = registerForActivityResult(
//        ActivityResultContracts.PickVisualMedia()
//    ) {
//        try {
//            val uri: Uri = it ?: throw Exception("Image is missing")
//            CoroutineScope(Dispatchers.IO).launch {
//
//                val imageBitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                    ImageDecoder.decodeBitmap(
//                        ImageDecoder.createSource(
//                            requireContext().contentResolver,
//                            uri
//                        )
//                    )
//                } else {
//                    MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
//                }
//
//                imageBitmap.saveImage()
//            }
//        } catch (e: Exception) {
//            Log.e("setPhotoProfile", e.toString())
//            Toast.makeText(activity, "Image is missing", Toast.LENGTH_LONG).show()
//        }
//    }
//
//    private fun Bitmap.saveImage() {
//        val destinationPath =
//            File(requireActivity().getExternalFilesDir("image"), "photoProfile.jpg")
//        val fileOutputStream = destinationPath.outputStream() //destination
//        this.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
//        fileOutputStream.close()
//
//        val path = destinationPath.path
//        Log.d("savedImagePath", "path: $path")
//        CoroutineScope(Dispatchers.Main).launch {
//            Glide
//                .with(requireActivity())
//                .load(path)
//                .into(binding.ivProfilePicture)
//        }
//    }

}

