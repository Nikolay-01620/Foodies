package com.foodies.basket_feature.di

import androidx.lifecycle.ViewModel
import com.foodies.basket_feature.presentation.BasketViewModel
import com.foodies.core_ui.view_model.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface BasketViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BasketViewModel::class)
    fun bindBasketViewModel(viewModel: BasketViewModel): ViewModel


}