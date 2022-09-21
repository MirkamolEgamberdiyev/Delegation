package com.mirkamol.delegation.delegation

import com.mirkamol.delegation.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

interface HomeViewModelDelegate {
    suspend fun setEventList(list: List<User>)
    var userList: Flow<List<User>>
    fun errorMessage(error: String)
    val errorMessage: Flow<String>
}

@Singleton
class HomeViewModelDelegateImpl @Inject constructor() : HomeViewModelDelegate {

    private val _userList = MutableStateFlow<List<User>>(listOf())
    override var userList: Flow<List<User>> = _userList


    override suspend fun setEventList(list: List<User>) {
        _userList.value = list
    }

    private val _errorMessage = MutableStateFlow<String>("")
    override val errorMessage: Flow<String> = _errorMessage
    override fun errorMessage(error: String) {
        _errorMessage.value = errorMessage.toString()
    }


}