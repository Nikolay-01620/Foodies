package com.foodies.core_ui.ui.components.catalog.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.foodies.core_ui.model.Category
import com.foodies.core_ui.ui.GreyBg
import com.foodies.core_ui.ui.OrangePrimary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CategoriesLine(
    lazyRowState: LazyListState,
    categoriesList: List<Category>,
    setSelectedCategory: (Category) -> Unit,
    selectedItem: Category?,
    coroutineScope: CoroutineScope
) {
    LazyRow(
        modifier = Modifier.padding(bottom = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = lazyRowState,
    ) {
        items(categoriesList.size) {
            val item = categoriesList[it]
            val color = if (selectedItem == item) OrangePrimary else GreyBg

            Text(
                modifier = Modifier
                    .background(shape = RoundedCornerShape(size = 16.dp), color = color)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .clickable {
                        setSelectedCategory(item)
                        coroutineScope.launch {
                            lazyRowState.animateScrollToItem(it)
                        }
                    },
                text = item.name,
            )
        }
    }


}