package com.foodies.repository.domain.repositories_impl

import android.content.SharedPreferences
import androidx.core.content.edit
import com.foodies.core.model.ProductResponse
import com.foodies.repository.domain.repository.LocalRepository
import com.foodies.repository.model.remote.ProductDomain
import com.foodies.repository.utils.toProductDomain
import com.foodies.repository.utils.toProductResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.reflect.Type
import javax.inject.Inject


class LocalRepositoryImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    LocalRepository {
    val type: Type = object : TypeToken<List<ProductResponse>>() {}.type

    val gson = Gson()

    companion object {
        const val GET_PRODUCT_KEY = "getProduct"
    }


    override suspend fun addItem(product: ProductDomain) {
        val itemsList = getProducts().toMutableList()
        if (itemsList.find { it.id == product.id } == null) {
            val newProduct = product.copy(
                count = if (product.count > 0) {
                    product.count

                } else 1
            )
            itemsList.add(newProduct.toProductResponse())
            saveProducts(itemsList)
        }
    }

    override suspend fun deleteItem(product: ProductDomain) {
        val itemsList = getProducts().toMutableList()
        itemsList.remove(product.toProductResponse())
        saveProducts(itemsList)
    }

    override suspend fun getItems(): List<ProductDomain> {
        return withContext(Dispatchers.IO) {
            getProducts().map {
                it.toProductDomain()
            }
        }
    }

    override suspend fun updateItem(product: ProductDomain) {
        val itemsList = getProducts().toMutableList()
        val foundedIndex = itemsList.indexOfFirst { it.id == product.id }
        itemsList[foundedIndex] = product.toProductResponse()
        saveProducts(itemsList)
    }

    private fun getProducts(): List<ProductResponse> {
        val productResponse = sharedPreferences.getString(GET_PRODUCT_KEY, "")
        return try {
            gson.fromJson(productResponse, type)

        } catch (e: Exception) {
            emptyList<ProductResponse>()
        }
    }

    private fun saveProducts(products: List<ProductResponse>) {
        sharedPreferences.edit {
            putString(GET_PRODUCT_KEY, gson.toJson(products))
        }
    }
}
