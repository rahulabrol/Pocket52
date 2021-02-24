package com.rahulabrol.pocket52test.di

import com.rahulabrol.pocket52test.network.PocketClient
import com.rahulabrol.pocket52test.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

/**
 * Created by Rahul Abrol on 24/2/21.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideHomeRepository(pocketClient: PocketClient): HomeRepository {
        return HomeRepository(pocketClient)
    }
}