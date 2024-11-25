package com.foodies.repository.domain.repository

import com.foodies.repository.model.remote.CategoryDomain
import com.foodies.repository.model.remote.ProductDomain
import com.foodies.repository.model.remote.TagDomain

interface RemoteRepository {
    suspend fun getProducts(): List<ProductDomain>
    suspend fun getCategory(): List<CategoryDomain>
    suspend fun getTags(): List<TagDomain>
}