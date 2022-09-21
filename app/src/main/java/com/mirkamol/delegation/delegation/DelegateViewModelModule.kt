package com.mirkamol.delegation.delegation

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DelegateViewModelModule {
    @Provides
    @Singleton
    fun provideHomeViewModelDelegate(): HomeViewModelDelegate =
        HomeViewModelDelegateImpl()

    @Provides
    @Singleton
    fun provideTvShowsViewModelDelegate(): TVshowViewModelDelegate =
        TVshowViewModelDelegateImpl()
}