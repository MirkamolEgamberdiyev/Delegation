package com.mirkamol.delegation.di

import android.content.Context
import androidx.room.Room
import com.mirkamol.delegation.local.dao.UserDao
import com.mirkamol.delegation.local.database.UserDatabase
import com.mirkamol.delegation.network.Server
import com.mirkamol.delegation.network.TVshowService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    /**
     * Room Related
     */
    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, "user.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: UserDatabase): UserDao = appDatabase.userDao()

    /**
     * Retrofit Related
     */

    @Provides
    @Singleton
    fun retrofitClient(): Retrofit {
        return Retrofit.Builder().baseUrl(Server.getDevelopment())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun tvShowService(): TVshowService {
        return retrofitClient().create(TVshowService::class.java)
    }

}