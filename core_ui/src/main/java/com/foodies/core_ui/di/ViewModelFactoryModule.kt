package com.foodies.core_ui.di

import androidx.lifecycle.ViewModelProvider
import com.foodies.core_ui.view_model.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
