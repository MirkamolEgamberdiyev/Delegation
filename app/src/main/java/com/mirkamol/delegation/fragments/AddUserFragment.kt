package com.mirkamol.delegation.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mirkamol.delegation.R
import com.mirkamol.delegation.databinding.FragmentAddUserBinding
import com.mirkamol.delegation.model.User
import com.mirkamol.delegation.utils.load
import com.mirkamol.delegation.viewmodel.AddUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserFragment : Fragment(R.layout.fragment_add_user) {
    private val binding by viewBinding(FragmentAddUserBinding::bind)
    private val viewModel: AddUserViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.itemPluss.setOnClickListener {
            openGallery()
        }
        binding.toolbar.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(intent, REQUEST_CODE)
    }

    companion object {
        const val REQUEST_CODE = 100
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            if (data?.clipData != null) {
                val count: Int = data.clipData!!.itemCount
                for (i in 0 until count) {
                    val imageUri: Uri = data.clipData!!.getItemAt(i).uri
                    binding.ivImages.load(imageUri.toString())

                    binding.apply {
                        btnUpload.setOnClickListener {
                            val title = tvTitle.text.toString().trim()
                            val description = tvDescription.text.toString().trim()

                            if (title.isNotEmpty() && description.isNotEmpty() && imageUri.toString()
                                    .isNotEmpty()
                            ) {
                                viewModel.addUser(
                                    User(
                                        title = title,
                                        description = description,
                                        image = imageUri.toString()
                                    )
                                )
                                findNavController().navigate(R.id.homeFragment)
                            } else {
                                Toast.makeText(requireContext(), "Enter User", Toast.LENGTH_SHORT)
                                    .show()
                            }

                        }
                    }
                }
                binding.itemPluss.visibility = View.GONE
            }
        }
    }


}