package com.foodies.feature_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodies.core_ui.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel() : ViewModel() {

    private val _basketItems = MutableStateFlow<List<Product>>(emptyList())
    val basketItems: StateFlow<List<Product>> = _basketItems.asStateFlow()

    private val _currentPrice = MutableStateFlow<String>("")
    val currentPrice: StateFlow<String> = _currentPrice.asStateFlow()


    fun addItem(product: Product) {
        viewModelScope.launch {
            //sharedPreferencesRepository.addItem(product.toProductDomain())
            getItems()  // Обновляем список
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