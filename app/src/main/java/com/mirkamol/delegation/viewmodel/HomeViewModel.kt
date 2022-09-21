package com.mirkamol.delegation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirkamol.delegation.delegation.HomeViewModelDelegate
import com.mirkamol.delegation.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MainRepository,
    homeViewModelDelegate: HomeViewModelDelegate
) : ViewModel(),
    HomeViewModelDelegate by homeViewModelDelegate {


    @ExperimentalCoroutinesApi
    fun fetchUsers() {
        viewModelScope.launch {
            repository.getUsers()
                .catch { e ->
                    errorMessage(e.message.toString())
                }
                .collect {
                    setEventList(it)
                }
        }
    }


}



