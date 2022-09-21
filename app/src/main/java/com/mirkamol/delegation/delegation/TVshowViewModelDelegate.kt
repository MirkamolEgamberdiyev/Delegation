package com.mirkamol.delegation.delegation

import com.mirkamol.delegation.model.TVShow
import com.mirkamol.delegation.model.TVShowPopular
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

interface TVshowViewModelDelegate {
    suspend fun tvShowsFromApi(list: ArrayList<TVShow>)
    var tvShowList: Flow<ArrayList<TVShow>>
    suspend fun tvShowPopular(tvShowPopular: TVShowPopular)
    var tvShowPopular: Flow<TVShowPopular>
    fun isLoading(isLoading: Boolean)
    val isLoading: Flow<Boolean>
    fun errorMessage(error: String)
    val errorMessage: Flow<String>

}

@Singleton
class TVshowViewModelDelegateImpl @Inject constructor() :
    TVshowViewModelDelegate {

    private val _tvShowList = MutableStateFlow<ArrayList<TVShow>>(arrayListOf())
    override var tvShowList: Flow<ArrayList<TVShow>> = _tvShowList

    override suspend fun tvShowsFromApi(list: ArrayList<TVShow>) {
        _tvShowList.value = list
    }

    private val _tvshowPopular = MutableStateFlow<TVShowPopular>(TVShowPopular())
    override var tvShowPopular: Flow<TVShowPopular> = _tvshowPopular

    override suspend fun tvShowPopular(tvShowPopular: TVShowPopular) {
        _tvshowPopular.value = tvShowPopular
    }


    private val _isLoading = MutableStateFlow<Boolean>(false)
    override val isLoading: Flow<Boolean> = _isLoading

    override fun isLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    private val _errorMessage = MutableStateFlow<String>("")
    override val errorMessage: Flow<String> = _errorMessage

    override fun errorMessage(error: String) {
        _errorMessage.value = errorMessage.toString()
    }

}