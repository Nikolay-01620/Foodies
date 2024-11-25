package com.foodies.core_ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodies.core_ui.model.Product
import com.foodies.core_ui.utils.toImmutableList
import com.foodies.core_ui.utils.toProduct
import com.foodies.core_ui.utils.toProductDomain
import com.foodies.repository.domain.repository.LocalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BaseViewModel @Inject constructor(private val localRepository: LocalRepository) :
    ViewModel() {


    private val _items = MutableStateFlow<List<Product>>(emptyList())
    val items: StateFlow<List<Product>> = _items.asStateFlow()

    private val _currentPrice = MutableStateFlow("")
    val currentPrice: StateFlow<String> = _currentPrice.asStateFlow()

    private val _productList = MutableStateFlow<List<Product>>(emptyList())
    val productList: StateFlow<List<Product>> = _productList.asStateFlow()


    fun incItemsCount(product: Product) {
        val productList = _productList.value.toMutableList()
        val index = productList.indexOf(product)
        if (index != -1) {
            val newProduct = product.copy(count = product.count + 1)
            productList[index] = newProduct
            _productList.value = productList
            getPriceSum()
        }
    }


    fun addItem(product: Product) {
        viewModelScope.launch {
            localRepository.addItem(product.toProductDomain())
            getItems()
        }
    }

    fun decItemsCount(product: Product) {
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

    private fun getItems() {
        viewModelScope.launch {
            val list = localRepository.getItems().map {
                it.toProduct()
            }.toImmutableList()
            _items.value = list
            setCartPrice()
        }
    }
    private fun setCartPrice() {
        viewModelScope.launch {
            var finalPrice = 0
            _items.value.forEach { productBasket ->
                finalPrice += productBasket.priceCurrent * productBasket.count
            }
            _currentPrice.value = finalPrice.toString()
        }
    }
}
