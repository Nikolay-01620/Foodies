package com.foodies.repository.di

import com.foodies.repository.domain.repositories_impl.LocalRepositoryImpl
import com.foodies.repository.domain.repositories_impl.RemoteRepositoryImpl
import com.foodies.repository.domain.repository.LocalRepository
import com.foodies.repository.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface RepositoriesModule {

    @Singleton
    @Binds
    fun provideRemoteRepository(remoteRepositoryImpl: RemoteRepositoryImpl): RemoteRepository

    @Singleton
    @Binds
    fun provideLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository

}