package com.foodies.core.source

import com.foodies.core.di.network.IRetrofitProvider
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Inject

/**
 * Основной класс для создания Retrofit объектов запроса в API Rick and Morty
 */
class RetrofitImpl @Inject constructor() : IRetrofitProvider {

    private val baseUrl = "https://anika1d.github.io/WorkTestServer/"
    override fun getFoodiesService(): FoodiesService =
        getRetrofit().create(FoodiesService::class.java)

    override fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(createOkHttpClient(MyInterceptor()))
            .build()
    }


    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder().let {
            it.addInterceptor(interceptor)
            it.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return httpClient.build()
    }

    class MyInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request())
        }
    }


}