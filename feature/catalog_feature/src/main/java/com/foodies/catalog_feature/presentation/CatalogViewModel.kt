package com.foodies.catalog_feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodies.core_ui.model.TagInApp
import com.foodies.core_ui.utils.toImmutableList
import com.foodies.catalog_feature.model.CategoryCatalog
import com.foodies.catalog_feature.model.ProductCatalog
import com.foodies.catalog_feature.model.TagInAppCatalog
import com.foodies.catalog_feature.utils.toCategoryCatalog
import com.foodies.catalog_feature.utils.toProductCatalog
import com.foodies.catalog_feature.utils.toProductDomain
import com.foodies.catalog_feature.utils.toTag
import com.foodies.repository.domain.repository.LocalRepository
import com.foodies.repository.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class CatalogViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) :
    ViewModel() {

    private val _selectedCategory = MutableStateFlow<CategoryCatalog?>(null)
    val selectedCategory: StateFlow<CategoryCatalog?> = _selectedCategory.asStateFlow()

    private val _productList = MutableStateFlow<List<ProductCatalog>>(emptyList())
    val productList: StateFlow<List<ProductCatalog>> = _productList.asStateFlow()

    private val _categoriesList = MutableStateFlow<List<CategoryCatalog>>(emptyList())
    val categoriesList: StateFlow<List<CategoryCatalog>> = _categoriesList.asStateFlow()

    private val _currentPrice = MutableStateFlow("")
    val currentPrice: StateFlow<String> = _currentPrice.asStateFlow()

    private val _selectedTags = MutableStateFlow<List<TagInAppCatalog>>(emptyList())
    val selectedTags: StateFlow<List<TagInAppCatalog>> = _selectedTags.asStateFlow()

    private val _catalogItems = MutableStateFlow<List<ProductCatalog>>(emptyList())
    val catalogItems: StateFlow<List<ProductCatalog>> = _catalogItems.asStateFlow()


    private var defaultProductList: List<ProductCatalog> = emptyList()


    // ОБЩИЕ ФУНКЦИИ КОТОРЫЕ БУДУТ ВЫНОСИТЬСЯ В BasicViewModel
////////////////////////////////////////////////////////////////////////////////////////////

    fun addItem(product: ProductCatalog) {
        viewModelScope.launch {
            localRepository.addItem(product.toProductDomain())
            getItems()
        }
    }

    private fun getItems() {
        viewModelScope.launch {
            val list = localRepository.getItems().map {
                it.toProductCatalog()
            }.toImmutableList()
            _catalogItems.value = list
            setCartPrice()
        }
    }

    private fun setCartPrice() {
        viewModelScope.launch {
            var finalPrice = 0
            _catalogItems.value.forEach { productBasket ->
                finalPrice += productBasket.priceCurrent * productBasket.count
            }
            _currentPrice.value = finalPrice.toString()
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////

    fun getProducts() {
        viewModelScope.launch {
            _productList.value = remoteRepository.getProducts().map {
                it.toProductCatalog()
            }
            defaultProductList = _productList.value
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            _categoriesList.value = remoteRepository.getCategory().map {
                it.toCategoryCatalog()
            }
            setSelectedCategory(_categoriesList.value.first())
        }
    }

    fun setSelectedCategory(category: CategoryCatalog) {
        _selectedCategory.value = category
        filterProducts()
    }

    fun getTags() {
        viewModelScope.launch {
            TagInApp.setList(
                remoteRepository.getTags().map {
                    it.toTag()
                }
            )
        }
    }

    fun incItemsCount(product: ProductCatalog) {
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
        product: ProductCatalog
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

    fun addSelectedTag(tagInApp: TagInAppCatalog) {
        val tagsList = _selectedTags.value.toMutableList()
        if (tagsList.find { it.id == tagInApp.id } == null)
            tagsList.add(tagInApp)
        _selectedTags.value = tagsList
    }

    fun onRemoveSelectedTag(tagInApp: TagInAppCatalog) {
        val tagsList = _selectedTags.value.toMutableList()
        if (tagsList.find { it.id == tagInApp.id } != null)
            tagsList.remove(tagInApp)
        _selectedTags.value = tagsList

    }

    fun applyFilters() {
        filterProducts()
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

    private fun filterProducts() {
        val category = _selectedCategory.value
        val tags = _selectedTags.value
        val filteredByCategory = defaultProductList.filter { it.categoryId == category?.id }
        val filteredByTags = if (tags.isEmpty()) {
            filteredByCategory
        } else {
            filteredByCategory.filter { product ->
                tags.any { product.tagIds.contains(it.id) }
            }
        }
        _productList.value = filteredByTags
    }
}