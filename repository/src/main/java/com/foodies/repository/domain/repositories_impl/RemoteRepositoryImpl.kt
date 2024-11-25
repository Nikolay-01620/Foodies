package com.foodies.repository.domain.repositories_impl

import com.foodies.core.source.FoodiesService
import com.foodies.repository.domain.repository.RemoteRepository
import com.foodies.repository.model.remote.CategoryDomain
import com.foodies.repository.model.remote.ProductDomain
import com.foodies.repository.model.remote.TagDomain
import com.foodies.repository.utils.toCategoryDomain
import com.foodies.repository.utils.toProductDomain
import com.foodies.repository.utils.toTagDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val foodiesService: FoodiesService,
) :
    RemoteRepository {
    override suspend fun getProducts(): List<ProductDomain> {
        return withContext(Dispatchers.IO) {
            foodiesService.getProducts().map {
                it.toProductDomain()
            }
        }
    }

    override suspend fun getCategory(): List<CategoryDomain> {
        return withContext(Dispatchers.IO) {
            foodiesService.getCategory().map {
                it.toCategoryDomain()
            }
        }
    }

    override suspend fun getTags(): List<TagDomain> {
        return withContext(Dispatchers.IO) {
            foodiesService.getTags().map {
                it.toTagDomain()
            }
        }
    }

}

