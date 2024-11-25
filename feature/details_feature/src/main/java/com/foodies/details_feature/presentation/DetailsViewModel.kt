package com.foodies.details_feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodies.core_ui.utils.toImmutableList
import com.foodies.details_feature.model.ProductDetails
import com.foodies.details_feature.utils.toProductDetails
import com.foodies.details_feature.utils.toProductDomain
import com.foodies.repository.domain.repository.LocalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val localRepository: LocalRepository) :
    ViewModel() {

    private val _detailsItems = MutableStateFlow<List<ProductDetails>>(emptyList())
    val basketItems: StateFlow<List<ProductDetails>> = _detailsItems.asStateFlow()

    private val _currentPrice = MutableStateFlow("")
    val currentPrice: StateFlow<String> = _currentPrice.asStateFlow()

    init {
        getItems()
    }

    fun addItem(product: ProductDetails) {
        viewModelScope.launch {
            localRepository.addItem(product.toProductDomain())
            getItems()
        }
    }

    private fun getItems() {
        viewModelScope.launch {
            val list = localRepository.getItems().map {
                it.toProductDetails()
            }.toImmutableList()
            _detailsItems.value = list
            setCartPrice()
        }
    }

    private fun setCartPrice() {
        viewModelScope.launch {
            var finalPrice = 0
            _detailsItems.value.forEach { product ->
                finalPrice += product.priceCurrent * product.count
            }
            _currentPrice.value = finalPrice.toString()
        }
    }
}
