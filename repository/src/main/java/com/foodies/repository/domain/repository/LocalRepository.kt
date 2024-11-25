package com.foodies.repository.domain.repository

import com.foodies.repository.model.remote.ProductDomain

interface LocalRepository {

    suspend fun addItem(product: ProductDomain)
    suspend fun deleteItem(product: ProductDomain)
    suspend fun getItems(): List<ProductDomain>
    suspend fun updateItem(product: ProductDomain)

}