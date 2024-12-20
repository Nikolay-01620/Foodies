package com.foodies.foodies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.foodies.basket_feature.presentation.BasketViewModel
import com.foodies.catalog_feature.presentation.CatalogViewModel
import com.foodies.core.di.app.App
import com.foodies.core_ui.view_model.BaseViewModel
import com.foodies.details_feature.presentation.DetailsViewModel
import com.foodies.foodies.di.MainActivityComponent
import com.foodies.foodies.navigation.NavHost
import com.foodies.search_feature.presentation.SearchViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject((application as App))
        setContent {
            NavHost(
                viewModelFactory = viewModelProvider
            )
        }
    }

    private fun inject(app: App) {
        MainActivityComponent.init(app.getApplicationProvider(), application)
            .inject(this)
    }
}

