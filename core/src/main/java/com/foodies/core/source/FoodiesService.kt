package com.foodies.core.source

import com.foodies.core.model.CategoryResponse
import com.foodies.core.model.ProductResponse
import com.foodies.core.model.TagResponse
import retrofit2.http.GET

interface FoodiesService {

    @GET("Products.json")
    suspend fun getProducts(): List<ProductResponse>

    @GET("Categories.json")
    suspend fun getCategory(): List<CategoryResponse>

    @GET("Tags.json")
    suspend fun getTags(): List<TagResponse>
}