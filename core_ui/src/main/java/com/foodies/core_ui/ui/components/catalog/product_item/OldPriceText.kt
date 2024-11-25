package com.foodies.core_ui.ui.components.catalog.product_item

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.foodies.core_ui.R

@Composable
fun OldPriceText(priceOld: Int) {
    Text(
        text = stringResource(R.string.item_price, priceOld / 100),
        style = MaterialTheme.typography.titleSmall,
        color = Color.Black.copy(alpha = 0.6f),
        textDecoration = TextDecoration.LineThrough,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}
