package com.foodies.core.di.network

import com.foodies.core.source.remote.FoodiesService
import com.foodies.core.source.remote.RetrofitImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class NetworkModule {

    @Binds
    abstract fun bindRetrofit(retrofit: RetrofitImpl): IRetrofitProvider

    companion object {
        @Provides
        fun provideRetrofitClient(retrofitImpl: RetrofitImpl): FoodiesService {
            return retrofitImpl.getFoodiesService()
        }
    }
}
