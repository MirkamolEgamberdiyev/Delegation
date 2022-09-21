package com.mirkamol.delegation.repository

import com.mirkamol.delegation.local.dao.UserDao
import com.mirkamol.delegation.model.User
import com.mirkamol.delegation.network.TVshowService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val userDao: UserDao,
    private val tVshowService: TVshowService
) {
    suspend fun addUser(user: User) = userDao.addFood(user)
    fun getUsers() = userDao.getUsers()
    suspend fun apiTVshowPopular(page: Int) = tVshowService.apiTVshowPopular(page)
    suspend fun apiTVshowPopular2(page: Int) = tVshowService.apiTVshowPopular2(page)


}