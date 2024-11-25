package com.foodies.core_ui.ui.components.catalog.basic

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.foodies.core_ui.R
import com.foodies.core_ui.model.Product
import com.foodies.core_ui.ui.GreyBg
import com.foodies.core_ui.ui.components.catalog.product_item.PlusMinusButton
import com.foodies.core_ui.ui.components.catalog.product_item.PriceButton
import com.foodies.core_ui.ui.components.catalog.product_item.ProductTags
import com.foodies.core_ui.ui.components.catalog.product_item.SaleTag

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
            .background(GreyBg, shape = RoundedCornerShape(size = 8.dp))
            .clickable { onProductClick(product) }
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.photo),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = product.name,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black.copy(alpha = 0.87f),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = "${product.measure} ${product.measureUnit}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black.copy(alpha = 0.6f)
            )

            if (!isCounterVisible) {
                PriceButton(
                    product = product,
                    onPriceClick = onPriceClick,
                    onPlusClick = onPlusClick,
                    onCounterVisibilityChange = { isVisible -> isCounterVisible = isVisible }
                )
            } else {
                PlusMinusButton(
                    product = product,
                    onPlusClick = onPlusClick,
                    onMinusClick = onMinusClick,
                    isCounterVisible = { isVisible -> isCounterVisible = isVisible }
                )
            }
        }
        SaleTag(isOnSale = product.priceOld != null)
        ProductTags(tagIds = product.tagIds)
    }
}
