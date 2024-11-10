package com.foodies.feature_catalog.di

import androidx.lifecycle.ViewModel
import com.foodies.core_ui.view_model.ViewModelKey
import com.foodies.feature_catalog.presentation.CatalogViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CatalogViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CatalogViewModel::class)
    fun bindCatalogViewModel(viewModel: CatalogViewModel): ViewModel


}