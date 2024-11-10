package com.foodies.feature_splash.di

import com.foodies.core.di.app.ApplicationProvider
import com.foodies.core_ui.di.NavigationModule
import dagger.Component


@Component(
    dependencies = [ApplicationProvider::class],
    modules = [NavigationModule::class]
)
interface SplashComponent {

    companion object {
        fun init(
            applicationProvider: ApplicationProvider
        )
                : SplashComponent {
            return DaggerSplashComponent.factory()
                .create(applicationProvider)

        }

    }

    @Component.Factory
    interface Factory {
        fun create(
            applicationProvider: ApplicationProvider
        ): SplashComponent
    }
}