package com.foodies.core.source.remote

import com.foodies.core.model.remote.CategoryResponse
import com.foodies.core.model.remote.ProductResponse
import com.foodies.core.model.remote.TagResponse
import retrofit2.http.GET

interface FoodiesService {

    @GET("Products.json")
    suspend fun getProducts(): List<ProductResponse>

    @GET("Categories.json")
    suspend fun getCategory(): List<CategoryResponse>

    @GET("Tags.json")
    suspend fun getTags(): List<TagResponse>
}