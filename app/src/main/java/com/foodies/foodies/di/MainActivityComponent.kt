package com.foodies.foodies.di

import android.app.Application
import com.foodies.core.di.app.ApplicationProvider
import com.foodies.core.di.shared_preferences.SharedPreferencesModule
import com.foodies.core.di.network.NetworkModule
import com.foodies.core_ui.di.NavigationModule
import com.foodies.core_ui.di.ViewModelFactoryModule
import com.foodies.foodies.MainActivity
import com.foodies.repository.di.RepositoriesModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NavigationModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        RepositoriesModule::class,
        NetworkModule::class,
        SharedPreferencesModule::class,
    ],
    dependencies = [
        ApplicationProvider::class,
    ]
)
interface MainActivityComponent {

    companion object {
        fun init(applicationProvider: ApplicationProvider,application: Application): MainActivityComponent {
            return DaggerMainActivityComponent.factory()
                .create(applicationProvider,SharedPreferencesModule(application))
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            applicationProvider: ApplicationProvider,
            sharedPreferencesModule: SharedPreferencesModule
        ): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}