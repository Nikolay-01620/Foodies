package com.foodies.foodies.di

import com.foodies.core.di.android.AndroidDependenciesComponent
import com.foodies.core.di.app.AndroidDependenciesProvider
import com.foodies.core.di.app.ApplicationProvider
import com.foodies.core.di.network.NetworkComponent
import com.foodies.core.di.network.NetworkProvider
import com.foodies.core_ui.di.ViewModelFactoryModule
import com.foodies.foodies.FoodiesApp
import dagger.Component

@Component(
    dependencies = [
        AndroidDependenciesProvider::class,
        NetworkProvider::class
    ],
    modules = [
        ApplicationModule::class
    ]
)
interface ApplicationComponent: ApplicationProvider {

    companion object {

        fun init(app: FoodiesApp): ApplicationProvider {

            val androidDependenciesProvider = AndroidDependenciesComponent.create(app)
            val networkProvider = NetworkComponent.create()

            return DaggerApplicationComponent.factory()
                .create(
                    androidDependenciesProvider,
                    networkProvider,
                    ApplicationModule(app)
                )
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            androidDependenciesProvider: AndroidDependenciesProvider,
            networkProvider: NetworkProvider,
            applicationModule: ApplicationModule
        ): ApplicationComponent
    }
}