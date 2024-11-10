package com.foodies.core_ui.di

import androidx.navigation.NavHost
import dagger.Binds
import dagger.Module

@Module
interface NavigationModule {
    @Binds
    fun bindRouter(navHost: NavHost): NavHost

}