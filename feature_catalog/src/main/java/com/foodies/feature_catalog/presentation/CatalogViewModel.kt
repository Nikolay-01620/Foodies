package com.foodies.feature_catalog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodies.core_ui.model.Product
import com.foodies.core_ui.model.Category
import com.foodies.core_ui.model.TagInApp
import com.foodies.feature_catalog.utils.toCategory
import com.foodies.feature_catalog.utils.toProduct
import com.foodies.repository.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class CatalogViewModel @Inject constructor(private val remoteRepository: RemoteRepository) :
    ViewModel() {

    private val _selectedCategory = MutableStateFlow<Category?>(null)
    val selectedCategory: StateFlow<Category?> = _selectedCategory.asStateFlow()

    private val _productList = MutableStateFlow<List<Product>>(emptyList())
    val productList: StateFlow<List<Product>> = _productList.asStateFlow()

    private val _categoriesList = MutableStateFlow<List<Category>>(emptyList())
    val categoriesList: StateFlow<List<Category>> = _categoriesList.asStateFlow()

    private val _currentPrice = MutableStateFlow<String>("")
    val currentPrice: StateFlow<String> = _currentPrice.asStateFlow()

    private val _selectedTags = MutableStateFlow<List<TagInApp>>(emptyList())
    val selectedTags: StateFlow<List<TagInApp>> = _selectedTags.asStateFlow()

    private var defaultProductList: List<Product> = emptyList()

    fun getProducts() {
        viewModelScope.launch {
            _productList.value = remoteRepository.getProducts().map {
                it.toProduct()
            }
            defaultProductList = _productList.value
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            _categoriesList.value = remoteRepository.getCategory().map {
                it.toCategory()
            }
            setSelectedCategory(_categoriesList.value.first())
        }
    }


    fun setSelectedCategory(category: Category) {
        _selectedCategory.value = category
        filterProducts()
    }

    fun getTags() {
        viewModelScope.launch {
            /*TagInApp.setList(
                remoteRepository.getTags()
            )*/
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

        //получение индекса
        val index = productList.indexOf(product)
        val newProduct = product.copy(count = product.count - 1)

        productList[index] = newProduct
        _productList.value = productList
        getPriceSum()
    }

    fun addSelectedTag(tagInApp: TagInApp) {
        val tagsList = _selectedTags.value.toMutableList()
        if (tagsList.find { it.id == tagInApp.id } == null)
            tagsList.add(tagInApp)
        _selectedTags.value = tagsList
    }

    fun onRemoveSelectedTag(tagInApp: TagInApp) {
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
                if (tags.isEmpty()) {
                    return@filter true
                }
                if (product.priceOld != null && tags.find { it.id == 0 } != null) {
                    return@filter true
                }
                tags.forEach {
                    if (product.tagIds.contains(it.id)) {
                        return@filter true
                    }
                }
                false
            }
        }
        _productList.value = filteredByTags
    }
}

/* val filteredByTags = if (tags.isEmpty()) {
    // Если теги не выбраны, возвращаем продукты, отфильтрованные только по категории
    filteredByCategory
} else {
    // Иначе фильтруем продукты, отфильтрованные по категории, дополнительно по тегам
    filteredByCategory.filter { product ->
        // Проверка на наличие скидки и выбранный тег со скидкой
        if (product.priceOld != null && tags.find { it.id == 0 } != null) {
            return@filter true
        }
        // Проверка на соответствие продуктовых тегов выбранным тегам
        tags.any { product.tagIds.contains(it.id) }
    }
}
// Обновление списка продуктов
_productList.value = filteredByTags*/


/*val filteredByTags = if (tags.isEmpty()) {
    filteredByCategory
} else {
    filteredByCategory.filter { product ->
        tags.any { product.tagIds.contains(it.id) }
    }
}
_productList.value = filteredByTags*/
