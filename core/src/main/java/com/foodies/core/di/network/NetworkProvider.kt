package com.foodies.core.di.network

import com.foodies.core.source.FoodiesService

interface NetworkProvider {
    fun getFoodiesService(): FoodiesService
}