package com.foodies.core.di.shared_preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule(private val application: Application) {
    companion object {
        private const val SHARED_PREFERENCES_NAME = "SharedPreferences"
    }

    @Provides
    fun providesSharedPreferences(): SharedPreferences {
        return application.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }
}