package com.foodies.core_ui.ui.components.product_item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.foodies.core_ui.R
import com.foodies.core_ui.model.Product
import com.foodies.core_ui.ui.GreyBg
import com.foodies.core_ui.ui.OrangePrimary

@Composable
fun PlusMinusButton(
    product: Product,
    onPlusClick: (Product) -> Unit,
    onMinusClick: (Product) -> Unit,
    isCounterVisible: (Boolean) -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        ElevatedButton(
            onClick = {
                if (product.count > 1) {
                    onMinusClick(product)
                } else {
                    isCounterVisible(false) // Скрываем счетчик, если он становится нулевым
                    onMinusClick(product)
                }
            },
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
            onClick = { onPlusClick(product) },
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
                contentDescription = stringResource(R.string.remove_item),
                tint = OrangePrimary
            )
        }
    }
}