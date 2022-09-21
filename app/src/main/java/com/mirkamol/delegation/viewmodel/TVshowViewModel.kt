package com.mirkamol.delegation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mirkamol.delegation.delegation.TVshowViewModelDelegate
import com.mirkamol.delegation.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TVshowViewModel @Inject constructor(
    private val repository: MainRepository,
    tVshowViewModelDelegate: TVshowViewModelDelegate
) : ViewModel(),
    TVshowViewModelDelegate by tVshowViewModelDelegate {


    fun fetchImages(page: Int) {
        isLoading(true)
        CoroutineScope(Dispatchers.IO).launch {
            val responce = repository.apiTVshowPopular2(page)
            withContext(Dispatchers.Main) {
                if (responce.isSuccessful) {
                    val result = responce.body()!!.tv_shows
                    tvShowPopular(responce.body()!!)
                    tvShowsFromApi(result!!)
                    isLoading(false)
                    Log.d("TAG", "fetchImages:$responce ")
                    Log.d("TAG", "fetchImages:${responce.body()!!.tv_shows} ")
                } else {
                    errorMessage("Error : ${responce.message()} ")
                }
            }
        }
    }




}