package com.foodies.core_ui.ui.components.basic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.foodies.core_ui.model.Product
import com.foodies.core_ui.route.Route
import com.google.gson.Gson

@Composable
fun ProductGrid(
    productsList: List<Product>,
    navController: NavController,
    isButtonVisible: (Boolean) -> Unit,
    onPlusClick: (Product) -> Unit,
    onMinusClick: (Product) -> Unit
) {
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
                onPlusClick = { onPlusClick(product) },
                onMinusClick = {
                    if (product.count <= 1) {
                        isButtonVisible(false)
                    }
                    onMinusClick(product)
                },
                onPriceClick = { isButtonVisible(true) }
            )
        }
    }
}


/*
onPlusClick = { catalogViewModel.incItemsCount(product) },
onMinusClick = {
    if (product.count <= 1) {
        isButtonVisible(false)
    }
    catalogViewModel.decItemsCount(product)
},
onPriceClick = { isButtonVisible(true) }*/
