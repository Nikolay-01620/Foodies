package com.foodies.details_feature.di

import com.foodies.core.di.app.ApplicationProvider
import com.foodies.core_ui.di.NavigationModule
import com.foodies.repository.di.RepositoriesModule
import dagger.Component

@Component(
    dependencies = [ApplicationProvider::class],
    modules = [
        NavigationModule::class,
        DetailsViewModelModule::class,
        RepositoriesModule::class
    ]
)
interface DetailsComponent {

    @Component.Factory
    interface Factory {
        fun create(
            applicationProvider: ApplicationProvider
        ): DetailsComponent
    }
}