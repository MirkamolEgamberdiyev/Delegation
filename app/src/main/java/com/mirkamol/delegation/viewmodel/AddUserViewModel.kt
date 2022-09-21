package com.mirkamol.delegation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirkamol.delegation.model.User
import com.mirkamol.delegation.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    fun addUser(user: User) = viewModelScope.launch {
        repository.addUser(user)
    }
}