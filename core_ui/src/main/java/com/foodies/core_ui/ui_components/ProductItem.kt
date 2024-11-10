package com.foodies.core_ui.ui_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.foodies.core_ui.model.Product
import com.foodies.core_ui.R
import com.foodies.core_ui.model.TagInApp

@Composable
fun ProductItem(
    product: Product,
    onProductClick: (Product) -> Unit,
    onPlusClick: (Product) -> Unit,
    onMinusClick: (Product) -> Unit,
    onPriceClick: () -> Unit
) {

    var isCounterVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .background(color = Color.Gray, shape = RoundedCornerShape(size = 8.dp))
            .clickable { onProductClick(product) }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.photo),
                contentDescription = null
            )


            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = product.name
            )
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = product.measure.toString(),
                color = Color.Gray
            )
            if (!isCounterVisible) {
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .background(Color.White, shape = RoundedCornerShape(size = 8.dp))
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                        .clickable {
                            onPriceClick()
                            onPlusClick(product)
                            isCounterVisible = true
                        }
                ) {

                    Text(
                        modifier = Modifier.padding(end = 4.dp),
                        text = product.priceCurrent.toString()
                    )
                    Text(
                        text = product.priceOld.toString(),
                        color = Color.Gray
                    )
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    ElevatedButton(
                        onClick = {
                            if (product.count > 1) {
                                onMinusClick(product)
                            } else {
                                isCounterVisible = false
                                onMinusClick(product)
                            }
                        },
                        modifier = Modifier
                            .size(40.dp),
                        shape = RoundedCornerShape(size = 8.dp),
                        colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.Gray),
                        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp),
                        border = BorderStroke(width = 0.dp, color = Color.Transparent),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.minus_icon),
                            contentDescription = stringResource(R.string.remove_item),
                            tint = Color.Yellow
                        )
                    }
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 20.dp), text = product.count.toString()
                    )

                    ElevatedButton(
                        onClick = { onPlusClick(product) },
                        modifier = Modifier
                            .size(40.dp),
                        shape = RoundedCornerShape(size = 8.dp),
                        colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.Gray),
                        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp),
                        border = BorderStroke(width = 0.dp, color = Color.Transparent),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.plus_icon),
                            contentDescription = stringResource(R.string.remove_item),
                            tint = Color.Yellow
                        )
                    }
                }
            }
        }
        Row {
            if (product.priceOld != null) {
                Image(
                    painter = painterResource(id = R.drawable.sale_tag),
                    contentDescription = stringResource(id = R.string.item_image),
                    modifier = Modifier
                        .padding(8.dp)
                        .size(20.dp)
                )
            }
            repeat(product.tagIds.size) {
                val iconId = product.tagIds[it]
                Image(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(20.dp),
                    painter = painterResource(
                        id = TagInApp.getIconId(iconId)
                    ),
                    contentDescription = null
                )
            }
        }
    }
}