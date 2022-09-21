package com.mirkamol.delegation.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mirkamol.delegation.R
import com.mirkamol.delegation.adapter.TVShowAdapter
import com.mirkamol.delegation.databinding.FragmentTvShowBinding
import com.mirkamol.delegation.model.TVShow
import com.mirkamol.delegation.viewmodel.TVshowViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvShowFragment : Fragment(R.layout.fragment_tv_show) {
    private val binding by viewBinding(FragmentTvShowBinding::bind)
    private val viewModel: TVshowViewModel by viewModels()
    lateinit var adapter: TVShowAdapter
    private val TAG = "TvShowFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

    }

    private fun initViews() {
        refreshAdapter(ArrayList())
        initObserves()
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvHome.layoutManager = gridLayoutManager
        binding.rvHome.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (gridLayoutManager.findLastCompletelyVisibleItemPosition() == adapter.itemCount - 1) {
                    lifecycleScope.launch {
                        viewModel.tvShowPopular.collect {
                            viewModel.fetchImages(it.page!! + 1)
                        }

                    }

                }
            }
        })
        viewModel.fetchImages(1)

    }

    private fun refreshAdapter(arrayList: ArrayList<TVShow>) {
        adapter = TVShowAdapter(this, arrayList)
        binding.rvHome.adapter = adapter
    }

    private fun initObserves() {
        lifecycleScope.launch {
            viewModel.isLoading.collect {
                if (it) {
                    binding.pbLoading.visibility = View.VISIBLE
                } else {
                    binding.pbLoading.visibility = View.GONE
                }
            }
        }
        lifecycleScope.launch {
            viewModel.tvShowList.collect {
                adapter.setViewTVShows(it)
            }
        }
        lifecycleScope.launch {
            viewModel.errorMessage.collect {
                Log.d(TAG, "initObserves: $it")
            }
        }
    }

}