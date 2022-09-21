package com.mirkamol.delegation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mirkamol.delegation.R
import com.mirkamol.delegation.adapter.HomeAdapter
import com.mirkamol.delegation.databinding.FragmentHomeBinding
import com.mirkamol.delegation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    lateinit var adapter: HomeAdapter

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        adapter = HomeAdapter {
            findNavController().navigate(R.id.tvShowFragment)
        }
        loadRecourse()
        viewModel.fetchUsers()
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.addUserFragment)
        }


    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun loadRecourse() {
        lifecycleScope.launch {
            viewModel.userList.collect {
                adapter.submitList(it)
            }
            if (viewModel.userList.first().isEmpty()) {
                viewModel.fetchUsers()
            }
        }
        binding.recyclerView.adapter = adapter
    }

}