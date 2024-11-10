package com.foodies.feature_search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodies.core_ui.model.Product
import com.foodies.repository.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private val _filteredProducts = MutableStateFlow<List<Product>>(emptyList())
    private var allProducts = emptyList<Product>()
    private val _searchResults = MutableStateFlow<List<Product>>(emptyList())
    val searchResults: StateFlow<List<Product>> = _searchResults

    private val _productList = MutableStateFlow<List<Product>>(emptyList())
    val productList: StateFlow<List<Product>> = _productList.asStateFlow()

    private val _currentPrice = MutableStateFlow<String>("")
    val currentPrice: StateFlow<String> = _currentPrice.asStateFlow()


    init {
        viewModelScope.launch {
          /*  allProducts = remoteRepository.getProducts().map { it.toProduct() }
            _filteredProducts.value = allProducts*/
        }
    }


    fun incItemsCount(product: Product) {
        val productList = _productList.value.toMutableList()
        val index = productList.indexOf(product)
        val newProduct = product.copy(count = product.count + 1)
        productList[index] = newProduct
        _productList.value = productList
        getPriceSum()
    }

    fun decItemsCount(
         product: Product
    ) {
        val productList = _productList.value.toMutableList()
        val index = productList.indexOf(product)
        val newProduct = product.copy(count = product.count - 1)

        productList[index] = newProduct
        _productList.value = productList
        getPriceSum()
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
        _searchResults.value = if (query.isEmpty()) {
            allProducts
        } else {
            allProducts.filter { it.name.contains(query, ignoreCase = true) }
        }
    }

}






















