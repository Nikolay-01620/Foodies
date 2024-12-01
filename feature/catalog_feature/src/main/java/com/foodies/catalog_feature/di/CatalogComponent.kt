package com.foodies.catalog_feature.di

import com.foodies.core.di.app.ApplicationProvider
import com.foodies.core_ui.di.NavigationModule
import com.foodies.repository.di.RepositoriesModule
import dagger.Component

@Component(
    dependencies = [ApplicationProvider::class],
    modules = [
        NavigationModule::class,
        CatalogViewModelModule::class,
        RepositoriesModule::class
    ]
)
interface CatalogComponent {

    @Component.Factory
    interface Factory {
        fun create(
            applicationProvider: ApplicationProvider
        ): CatalogComponent
    }
}