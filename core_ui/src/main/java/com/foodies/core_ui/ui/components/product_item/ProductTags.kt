package com.foodies.core_ui.ui.components.product_item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.foodies.core_ui.model.TagInApp

@Composable
fun ProductTags(tagIds: List<Int>) {
    Row {
        repeat(tagIds.size) {
            val iconId = tagIds[it]
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
