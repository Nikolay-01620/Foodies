package com.foodies.catalog_feature.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.foodies.core_ui.route.Route
import com.foodies.feature_catalog.R
import com.foodies.core_ui.ui.components.catalog.basic.CategoriesLine
import com.foodies.core_ui.ui.components.catalog.basic.Header
import com.foodies.core_ui.ui.components.catalog.basic.ProductGrid
import com.foodies.core_ui.ui.components.catalog.other.AppButton
import com.foodies.core_ui.view_model.BaseViewModel
import com.foodies.catalog_feature.utils.toCategory
import com.foodies.catalog_feature.utils.toCategoryCatalog
import com.foodies.catalog_feature.utils.toProduct
import com.foodies.catalog_feature.utils.toProductCatalog
import com.foodies.catalog_feature.utils.toTagInApp
import com.foodies.catalog_feature.utils.toTagInAppCatalog

@Composable
fun CatalogScreen(
    navController: NavController = rememberNavController(),
    viewModelProvider: ViewModelProvider.Factory
) {

    val catalogViewModel: CatalogViewModel = viewModel(
        factory = viewModelProvider
    )
    val basicViewModel: BaseViewModel = viewModel(
        factory = viewModelProvider
    )
    LaunchedEffect(key1 = Unit) {
        catalogViewModel.getProducts()
        catalogViewModel.getCategories()
        catalogViewModel.getTags()
    }
    val categoriesList by catalogViewModel.categoriesList.collectAsState()
    val productsList by catalogViewModel.productList.collectAsState()
    val selectedItem by catalogViewModel.selectedCategory.collectAsState()
    val currentPriceSum by catalogViewModel.currentPrice.collectAsState()
    val selectedTags by catalogViewModel.selectedTags.collectAsState()
    var isButtonVisible by remember { mutableStateOf(false) }

    val lazyRowState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Header(
            navController = navController,
            selectedTags = selectedTags.map { it.toTagInApp() },
            onAddFilter = { catalogViewModel.addSelectedTag(it.toTagInAppCatalog()) },
            onRemoveFilter = { catalogViewModel.onRemoveSelectedTag(it.toTagInAppCatalog()) },
            applyFilters = catalogViewModel::applyFilters
        )

        CategoriesLine(
            lazyRowState = lazyRowState,
            categoriesList = categoriesList.map { it.toCategory() },
            setSelectedCategory = { catalogViewModel.setSelectedCategory(it.toCategoryCatalog()) },
            selectedItem = selectedItem?.toCategory(),
            coroutineScope = coroutineScope,

            )
        Box(modifier = Modifier.weight(1f)) {
            ProductGrid(
                productsList = productsList.map { it.toProduct() },
                navController = navController,
                isButtonVisible = { isButtonVisible = it },
                onPlusClick = { catalogViewModel.incItemsCount(it.toProductCatalog()) },
                onMinusClick = { catalogViewModel.decItemsCount(it.toProductCatalog()) }
            )
        }
        if (isButtonVisible) {
            AppButton(
                onButtonClick = {
                    productsList.forEach {
                        if (it.count > 0) {
                            basicViewModel.addItem(it.toProduct())
                        }
                    }
                    navController.navigate(Route.BasketScreen.route)
                },
                text = stringResource(
                    id = R.string.in_cart_button_label,
                    currentPriceSum
                )
            )
        }
    }
}
