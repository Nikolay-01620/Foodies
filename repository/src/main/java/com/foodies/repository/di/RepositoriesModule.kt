package com.foodies.repository.di

import com.foodies.repository.domain.repositories_impl.LocalRepositoryImpl
import com.foodies.repository.domain.repositories_impl.RemoteRepositoryImpl
import com.foodies.repository.domain.repository.LocalRepository
import com.foodies.repository.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface RepositoriesModule {
    @Binds
    fun provideRemoteRepository(remoteRepositoryImpl: RemoteRepositoryImpl): RemoteRepository

    @Binds
    fun provideLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository

}