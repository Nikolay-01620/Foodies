package com.foodies.core.di.network

interface NetworkProvider {
    fun provideRetrofit(): IRetrofitProvider

}