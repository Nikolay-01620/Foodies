package com.foodies.feature_catalog.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.foodies.core_ui.navigation.Route
import com.foodies.core_ui.ui_components.AppButton
import com.foodies.core_ui.ui_components.ProductItem
import com.foodies.feature_catalog.R
import com.google.gson.Gson
import kotlinx.coroutines.launch

@Composable
fun CatalogScreen(
    navController: NavController = rememberNavController(),
    catalogViewModel: CatalogViewModel,
) {

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
    var isFilterDialogVisible by remember { mutableStateOf(false) }

    val lazyRowState = rememberLazyListState()

    val coroutineScope = rememberCoroutineScope()
    if (isFilterDialogVisible) {
        FilterDialog(
            selectedTags = selectedTags,
            onAddFilter = catalogViewModel::addSelectedTag,
            onRemoveFilter = catalogViewModel::onRemoveSelectedTag,
            onDismiss = {
                isFilterDialogVisible = false
                catalogViewModel.applyFilters()
            }

        )
    }
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Icon(
                modifier = Modifier.clickable { isFilterDialogVisible = true },
                painter = painterResource(id = R.drawable.filter_icon),
                contentDescription = null
            )
            Image(
                modifier = Modifier
                    .weight(1f),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
            Icon(
                modifier = Modifier.clickable { navController.navigate(Route.SearchScreen.route) },
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = null
            )
        }
        LazyRow(
            modifier = Modifier.padding(bottom = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            state = lazyRowState,
        ) {
            items(categoriesList.size) {
                val item = categoriesList[it]
                val color = if (selectedItem == item) Color.Yellow else Color.Gray

                Text(
                    modifier = Modifier
                        .background(shape = RoundedCornerShape(size = 16.dp), color = color)
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .clickable {
                            catalogViewModel.setSelectedCategory(item)
                            coroutineScope.launch {
                                lazyRowState.animateScrollToItem(it)
                            }
                        },
                    text = item.name,
                )
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            LazyVerticalGrid(
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(productsList.size) {
                    val product = productsList[it]
                    ProductItem(
                        product = product,
                        onProductClick = {
                            val json = Gson()
                            val jsonString = json.toJson(product)
                            navController.navigate(route = Route.DetailsScreen.route + "/$jsonString")
                        },
                        onPlusClick = { catalogViewModel.incItemsCount(product) },
                        onMinusClick = {
                            if (product.count <= 1) {
                                isButtonVisible = false
                            }
                            catalogViewModel.decItemsCount(product)
                        },
                        onPriceClick = { isButtonVisible = true }
                    )
                }
            }
        }
        if (isButtonVisible) {
            AppButton(
                onButtonClick = {
                    productsList.forEach {
                        if (it.count > 0) {
                            //catalogViewModel.addItem(it)
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
