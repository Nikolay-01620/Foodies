package com.foodies.core.di.network

import com.foodies.core.source.remote.FoodiesService
import retrofit2.Retrofit

interface IRetrofitProvider {
    fun getRetrofit(): Retrofit
    fun getFoodiesService(): FoodiesService
}