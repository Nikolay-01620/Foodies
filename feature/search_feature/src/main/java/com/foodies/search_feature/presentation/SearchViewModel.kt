package com.foodies.search_feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodies.search_feature.model.ProductSearch
import com.foodies.search_feature.utils.toProduct
import com.foodies.repository.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private val _filteredProducts = MutableStateFlow<List<ProductSearch>>(emptyList())
    private var allProducts = emptyList<ProductSearch>()

    private val _productList = MutableStateFlow<List<ProductSearch>>(emptyList())
    val productList: StateFlow<List<ProductSearch>> = _productList.asStateFlow()

    private val _currentPrice = MutableStateFlow("")
    val currentPrice: StateFlow<String> = _currentPrice.asStateFlow()


    init {
        viewModelScope.launch {
            allProducts = remoteRepository.getProducts().map { it.toProduct() }
            _filteredProducts.value = allProducts
        }
    }


    fun incItemsCount(product: ProductSearch) {
        val productList = _productList.value.toMutableList()
        val index = productList.indexOf(product)
        if (index != -1) {  // Проверяем, что индекс валиден
            val newProduct = product.copy(count = product.count + 1)
            productList[index] = newProduct
            _productList.value = productList
            getPriceSum()
        }
    }


    fun decItemsCount(
        product: ProductSearch
    ) {
        val productList = _productList.value.toMutableList()
        val index = productList.indexOf(product)
        if (index != -1) {
            val newProduct = product.copy(count = product.count - 1)
            productList[index] = newProduct
            _productList.value = productList
            getPriceSum()
        }
    }

    private fun getPriceSum() {
        viewModelScope.launch {
            var finalPrice = 0
            _productList.value.forEach { product ->
                finalPrice += product.priceCurrent * product.count
            }
            _currentPrice.value = finalPrice.toString()
        }
    }


    fun searchProducts(query: String) {
        _productList.value = if (query.isEmpty()) {
            allProducts
        } else {
            allProducts.filter { it.name.contains(query, ignoreCase = true) }
        }
    }

}






















