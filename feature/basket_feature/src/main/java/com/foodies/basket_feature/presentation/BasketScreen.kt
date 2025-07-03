package com.foodies.basket_feature.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.foodies.core_ui.ui.GreyBg
import com.foodies.core_ui.ui.OrangePrimary
import com.foodies.feature_basket.R
import com.foodies.basket_feature.model.ProductBasket

@Composable
fun BasketScreen(
    navController: NavController,
    viewModelProvider: ViewModelProvider.Factory
) {

    val basketViewModel: BasketViewModel = viewModel(
        factory = viewModelProvider
    )

    val basketItems by basketViewModel.basketItems.collectAsState()
    val currentPrice by basketViewModel.currentPrice.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 25.dp)
                .background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier
                    .clickable { navController.popBackStack() }
                    .padding(20.dp),
                painter = painterResource(id = R.drawable.arrow_left_icon),
                contentDescription = null
            )

            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = stringResource(R.string.basket),
                fontWeight = FontWeight.W600,
                fontSize = 18.sp
            )

        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(basketItems.size) {
                val basketItem = basketItems[it]
                BasketItem(
                    product = basketItem,
                    incItemsCount = basketViewModel::incItemsCount,
                    decItemsCount = basketViewModel::decItemsCount,
                )
            }
        }
        Button(modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(OrangePrimary),
            shape = RoundedCornerShape(size = 10.dp),
            onClick = {}) {

            Text(
                text = stringResource(
                    id = R.string.cart_order_button,
                    currentPrice.toIntOrNull()?.div(100) ?: 0
                ),
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )
        }

    }
}

@Composable
private fun BasketItem(
    product: ProductBasket,
    incItemsCount: (ProductBasket) -> Unit,
    decItemsCount: (ProductBasket) -> Unit,
) {
    Column {
        Row(modifier = Modifier.fillMaxWidth()) {

            Image(
                modifier = Modifier
                    .size(96.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.photo),
                contentDescription = null,

                )
            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .height(96.dp)
            ) {
                Text(text = product.name)
                Spacer(modifier = Modifier.weight(1f))
                Row {

                    ElevatedButton(
                        onClick = { decItemsCount(product) },
                        modifier = Modifier
                            .size(40.dp),
                        shape = RoundedCornerShape(size = 8.dp),
                        colors = ButtonDefaults.elevatedButtonColors(containerColor = GreyBg),
                        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp),
                        border = BorderStroke(width = 0.dp, color = Color.Transparent),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.minus_icon),
                            contentDescription = stringResource(R.string.remove_item),
                            tint = OrangePrimary
                        )
                    }
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 20.dp), text = product.count.toString()
                    )

                    ElevatedButton(
                        onClick = { incItemsCount(product) },
                        modifier = Modifier
                            .size(40.dp),
                        shape = RoundedCornerShape(size = 8.dp),
                        colors = ButtonDefaults.elevatedButtonColors(containerColor = GreyBg),
                        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp),
                        border = BorderStroke(width = 0.dp, color = Color.Transparent),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.plus_icon),
                            contentDescription = stringResource(R.string.add_item),
                            tint = OrangePrimary
                        )
                    }
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 16.dp),
                        text = stringResource(
                            com.foodies.core_ui.R.string.item_price,
                            product.priceCurrent * product.count / 100
                        ),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
    Spacer(
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.Gray)
    )
}