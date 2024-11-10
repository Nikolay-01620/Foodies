package com.foodies.feature_search.di

import androidx.lifecycle.ViewModel
import com.foodies.core_ui.view_model.ViewModelKey
import com.foodies.feature_search.presentation.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SearchViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel


}