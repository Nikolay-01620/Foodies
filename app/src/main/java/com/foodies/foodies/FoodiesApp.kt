package com.foodies.foodies

import android.app.Application
import com.foodies.core.di.app.App
import com.foodies.core.di.app.ApplicationProvider
import com.foodies.foodies.di.ApplicationComponent

class FoodiesApp : Application(), App {

    private lateinit var appComponent: ApplicationProvider


    override fun onCreate() {
        super.onCreate()
        appComponent = ApplicationComponent.init(this)
    }

    override fun getApplicationProvider(): ApplicationProvider {
        return appComponent
    }

}
