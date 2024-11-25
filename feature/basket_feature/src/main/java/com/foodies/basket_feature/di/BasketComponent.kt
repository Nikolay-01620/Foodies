package com.foodies.basket_feature.di

import com.foodies.core.di.app.ApplicationProvider
import com.foodies.core_ui.di.NavigationModule
import com.foodies.repository.di.RepositoriesModule
import dagger.Component

@Component(
    dependencies = [ApplicationProvider::class],
    modules = [
        NavigationModule::class,
        BasketViewModelModule::class,
        RepositoriesModule::class
    ]
)
interface BasketComponent {

    companion object {
        fun init(
            applicationProvider: ApplicationProvider
        )
                : BasketComponent {
            return DaggerBasketComponent.factory()
                .create(applicationProvider)

        }

    }

    @Component.Factory
    interface Factory {
        fun create(
            applicationProvider: ApplicationProvider
        ): BasketComponent
    }
}