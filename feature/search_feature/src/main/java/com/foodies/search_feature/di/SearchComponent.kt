package com.foodies.search_feature.di

import com.foodies.core.di.app.ApplicationProvider
import com.foodies.core_ui.di.NavigationModule
import com.foodies.repository.di.RepositoriesModule
import dagger.Component

@Component(
    dependencies = [ApplicationProvider::class],
    modules = [
        NavigationModule::class,
        SearchViewModelModule::class,
        RepositoriesModule::class
    ]
)
interface SearchComponent {

    companion object {
        fun init(
            applicationProvider: ApplicationProvider
        )
                : SearchComponent {
            return DaggerSearchComponent.factory()
                .create(applicationProvider)

        }

    }

    @Component.Factory
    interface Factory {
        fun create(
            applicationProvider: ApplicationProvider
        ): SearchComponent
    }
}