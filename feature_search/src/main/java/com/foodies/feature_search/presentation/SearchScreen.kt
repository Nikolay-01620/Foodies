package com.foodies.feature_search.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.foodies.core_ui.navigation.Route
import com.foodies.core_ui.ui_components.AppButton
import com.foodies.core_ui.ui_components.ProductItem
import com.foodies.feature_search.R
import com.google.gson.Gson

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel,
) {

    val catalogProducts by searchViewModel.productList.collectAsState()
    val searchResults by searchViewModel.searchResults.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var isButtonVisible by remember { mutableStateOf(false) }
    //val currentPriceSum by searchViewModel.currentPrice.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left_icon),
                contentDescription = null,
                modifier = Modifier
                    .clickable { navController.popBackStack() }
                    .padding(16.dp)
            )
            Text(text = "Search Results", modifier = Modifier.weight(1f))
        }

        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                searchViewModel.searchProducts(it) // Запуск поиска при изменении запроса
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            label = { Text(text = "Search") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide() // Закрываем клавиатуру
                    focusManager.clearFocus() // Снимаем фокус с поля ввода
                }
            )
        )

        Box(modifier = Modifier.weight(1f)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(searchResults.size) { index ->
                    val product = searchResults[index]
                    val catalogProduct = catalogProducts.find { it.id == product.id } ?: product
                    ProductItem(
                        product = catalogProduct,
                        onProductClick = {
                            val json = Gson()
                            val jsonString = json.toJson(catalogProduct)
                            navController.navigate(route = Route.DetailsScreen.route + "/$jsonString")
                        },
                        onPlusClick = {
                            //searchViewModel.incItemsCount(catalogProduct)
                        },
                        onMinusClick = {
                            if (catalogProduct.count <= 1) {
                                isButtonVisible = false
                            }
                            //searchViewModel.decItemsCount(catalogProduct)
                        },
                        onPriceClick = { isButtonVisible = true }
                    )
                }
            }
        }
        if (isButtonVisible) {
            AppButton(
                onButtonClick = {
                    /*catalogProducts.forEach {
                        if (it.count > 0) {
                            searchViewModel.incItemsCount(it)
                        }
                    }*/
                    navController.navigate(Route.BasketScreen.route)
                },
                text = stringResource(
                    id = R.string.in_cart_button_label,
                    //currentPriceSum
                )
            )
        }
    }
}
