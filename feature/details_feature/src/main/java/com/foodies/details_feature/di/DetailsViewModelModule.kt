package com.foodies.details_feature.di

import androidx.lifecycle.ViewModel
import com.foodies.core_ui.view_model.ViewModelKey
import com.foodies.details_feature.presentation.DetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DetailsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel
}