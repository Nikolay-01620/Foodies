package com.foodies.foodies.di

import com.foodies.core.di.android.AndroidDependenciesComponent
import com.foodies.core.di.app.AndroidDependenciesProvider
import com.foodies.core.di.app.ApplicationProvider
import com.foodies.foodies.FoodiesApp
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        AndroidDependenciesProvider::class,
    ],
    modules = [
        ApplicationModule::class,
    ]
)
interface ApplicationComponent : ApplicationProvider {
    companion object {
        fun init(app: FoodiesApp): ApplicationProvider {

            val androidDependenciesProvider = AndroidDependenciesComponent.create(app)
            return DaggerApplicationComponent.factory()
                .create(
                    androidDependenciesProvider,
                    ApplicationModule()
                )
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            androidDependenciesProvider: AndroidDependenciesProvider,
            applicationModule: ApplicationModule
        ): ApplicationComponent
    }
}