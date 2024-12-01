package com.foodies.core_ui.ui.components.product_item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.foodies.core_ui.R

@Composable
fun SaleTag(isOnSale: Boolean) {
    if (isOnSale) {
        Image(
            painter = painterResource(id = R.drawable.sale_tag),
            contentDescription = stringResource(id = R.string.item_image),
            modifier = Modifier
                .padding(8.dp)
                .size(20.dp)
        )
    }
}
