package com.foodies.basket_feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodies.core_ui.utils.toImmutableList
import com.foodies.basket_feature.model.ProductBasket
import com.foodies.basket_feature.utils.toProductBasket
import com.foodies.basket_feature.utils.toProductDomain
import com.foodies.repository.domain.repository.LocalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BasketViewModel @Inject constructor(private val localRepository: LocalRepository) :
    ViewModel() {

    private val _basketItems = MutableStateFlow<List<ProductBasket>>(emptyList())
    val basketItems: StateFlow<List<ProductBasket>> = _basketItems.asStateFlow()

    private val _currentPrice = MutableStateFlow("")
    val currentPrice: StateFlow<String> = _currentPrice.asStateFlow()

    init {
        getItems()
    }

    fun incItemsCount(product: ProductBasket) {
        val newProduct = product.copy(count = product.count + 1)
        viewModelScope.launch {
            localRepository.updateItem(newProduct.toProductDomain())
            getItems()
        }
    }

    fun decItemsCount(product: ProductBasket) {
        if (product.count > 1) {
            val newProduct = product.copy(count = product.count - 1)
            viewModelScope.launch {
                localRepository.updateItem(newProduct.toProductDomain())
                getItems()
            }
        } else deleteItem(product)
    }

    private fun deleteItem(product: ProductBasket) {
        viewModelScope.launch {
            localRepository.deleteItem(product.toProductDomain())
            getItems()
        }
    }

    private fun getItems() {
        viewModelScope.launch {
            val list = localRepository.getItems().map {
                it.toProductBasket()
            }.toImmutableList()
            _basketItems.value = list
            setCartPrice()
        }
    }

    private fun setCartPrice() {
        viewModelScope.launch {
            var finalPrice = 0
            _basketItems.value.forEach { productBasket ->
                finalPrice += productBasket.priceCurrent * productBasket.count
            }
            _currentPrice.value = finalPrice.toString()
        }
    }
}


