package com.foodies.splash_feature.di

import com.foodies.core.di.app.ApplicationProvider
import com.foodies.core_ui.di.NavigationModule
import dagger.Component

@Component(
    dependencies = [ApplicationProvider::class],
    modules = [NavigationModule::class]
)
interface SplashComponent {

    @Component.Factory
    interface Factory {
        fun create(
            applicationProvider: ApplicationProvider
        ): SplashComponent
    }
}