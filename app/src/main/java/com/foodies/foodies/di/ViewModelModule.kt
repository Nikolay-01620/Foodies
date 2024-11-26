package com.foodies.foodies.di


import androidx.lifecycle.ViewModel
import com.foodies.basket_feature.presentation.BasketViewModel
import com.foodies.catalog_feature.presentation.CatalogViewModel
import com.foodies.core_ui.view_model.BaseViewModel
import com.foodies.core_ui.view_model.ViewModelKey
import com.foodies.details_feature.presentation.DetailsViewModel
import com.foodies.search_feature.presentation.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CatalogViewModel::class)
    fun bindCatalogViewModel(viewModel: CatalogViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BasketViewModel::class)
    fun bindBasketViewModel(viewModel: BasketViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(BaseViewModel::class)
    fun bindBaseViewModel(viewModel: BaseViewModel): ViewModel


}
