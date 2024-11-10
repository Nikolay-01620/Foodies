package com.foodies.feature_basket.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodies.core_ui.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Collections
import javax.inject.Inject

class BasketViewModel @Inject constructor() :
    ViewModel() {

    private val _basketItems = MutableStateFlow<List<Product>>(emptyList())
    val basketItems: StateFlow<List<Product>> = _basketItems.asStateFlow()

    private val _currentPrice = MutableStateFlow<String>("")
    val currentPrice: StateFlow<String> = _currentPrice.asStateFlow()

    init {
        getItems()
    }

    fun addItem(product: Product) {
        viewModelScope.launch {
            //sharedPreferencesRepository.addItem(product.toProductDomain())
            getItems()  // Обновляем список
        }
    }

    fun incItemsCount(product: Product) {
        val newProduct = product.copy(count = product.count + 1)
        viewModelScope.launch {
           // sharedPreferencesRepository.updateItem(newProduct.toProductDomain())
            getItems()
        }
    }

    fun decItemsCount(product: Product) {
        if (product.count > 1) {
            val newProduct = product.copy(count = product.count - 1)
            viewModelScope.launch {
               // sharedPreferencesRepository.updateItem(newProduct.toProductDomain())
                getItems()
            }
        } else deleteItem(product)
    }

     fun deleteItem(product: Product) {
        viewModelScope.launch {
            //sharedPreferencesRepository.deleteItem(product.toProductDomain())
            getItems()
        }
    }

    private fun getItems() {
        viewModelScope.launch {
            /*val list = sharedPreferencesRepository.getItems().map {
                it.toProduct()
            }.toImmutableList()
            _basketItems.value = list
            setCartPrice()*/
        }
    }

    private fun setCartPrice() {
        viewModelScope.launch {
            var finalPrice = 0
            _basketItems.value.forEach { product ->
                finalPrice += product.priceCurrent * product.count
            }
            _currentPrice.value = finalPrice.toString()
        }
    }
}

fun <T> List<T>.toImmutableList(): List<T> {
    return Collections.unmodifiableList(toMutableList())
}


