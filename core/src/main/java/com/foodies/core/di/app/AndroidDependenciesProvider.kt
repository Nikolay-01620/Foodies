package com.foodies.core.di.app

import android.app.Application
import android.content.Context
import android.content.res.Resources

interface AndroidDependenciesProvider {

    fun provideContext(): Context

    fun provideResources(): Resources

    fun provideAppContext(): Application
}