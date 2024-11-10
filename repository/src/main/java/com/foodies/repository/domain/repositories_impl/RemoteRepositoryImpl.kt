package com.foodies.repository.domain.repositories_impl

import com.foodies.core.source.remote.FoodiesService
import com.foodies.repository.domain.repository.RemoteRepository
import com.foodies.repository.model.remote.CategoryDomain
import com.foodies.repository.model.remote.ProductDomain
import com.foodies.repository.model.remote.TagDomain
import com.foodies.repository.utils.toCategoryDomain
import com.foodies.repository.utils.toProductDomain
import com.foodies.repository.utils.toTagDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val foodiesService: FoodiesService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    RemoteRepository {
    override suspend fun getProducts(): List<ProductDomain> {
        return withContext(dispatcher) {
            foodiesService.getProducts().map {
                it.toProductDomain()
            }
        }
    }

    override suspend fun getCategory(): List<CategoryDomain> {
        return withContext(dispatcher) {
            foodiesService.getCategory().map {
                it.toCategoryDomain()
            }
        }
    }

    override suspend fun getTags(): List<TagDomain> {
        return withContext(dispatcher) {
            foodiesService.getTags().map {
                it.toTagDomain()
            }
        }
    }

}

