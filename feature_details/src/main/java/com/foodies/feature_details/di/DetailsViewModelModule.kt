package com.foodies.feature_details.di

import androidx.lifecycle.ViewModel
import com.foodies.core_ui.view_model.ViewModelKey
import com.foodies.feature_details.presentation.DetailsViewModel
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