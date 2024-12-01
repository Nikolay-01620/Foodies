package com.foodies.core_ui.ui.components.product_item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.foodies.core_ui.R
import com.foodies.core_ui.model.Product


@Composable
fun PriceButton(
    product: Product,
    onPriceClick: () -> Unit,
    onPlusClick: (Product) -> Unit,
    onCounterVisibilityChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(size = 8.dp))
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .fillMaxWidth()
            .clickable {
                onPriceClick()
                onPlusClick(product)
                onCounterVisibilityChange(true)
            },
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.item_price, product.priceCurrent / 100),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black.copy(alpha = 0.87f)
        )
        if (product.priceOld != null) {
            Spacer(modifier = Modifier.width(4.dp))
            OldPriceText(priceOld = product.priceOld)
        }
    }
}
