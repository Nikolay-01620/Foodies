package com.foodies.foodies.di

import com.foodies.core.di.app.ApplicationProvider
import com.foodies.core_ui.di.NavigationModule
import com.foodies.foodies.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NavigationModule::class
    ],
    dependencies = [
        ApplicationProvider::class,
    ]
)
interface MainActivityComponent {

    companion object {
        fun init(applicationProvider: ApplicationProvider): MainActivityComponent {
            return DaggerMainActivityComponent.factory()
                .create(applicationProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            applicationProvider: ApplicationProvider
        ): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}