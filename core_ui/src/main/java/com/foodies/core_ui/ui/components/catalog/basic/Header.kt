package com.foodies.core_ui.ui.components.catalog.basic

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.foodies.core_ui.R
import com.foodies.core_ui.model.TagInApp
import com.foodies.core_ui.route.Route
import com.foodies.core_ui.ui.components.catalog.other.FilterDialog

@Composable
fun Header(
    navController: NavController,
    selectedTags: List<TagInApp>,
    onAddFilter: (TagInApp) -> Unit,
    onRemoveFilter: (TagInApp) -> Unit,
    applyFilters: () -> Unit
) {
    var isFilterDialogVisible by remember { mutableStateOf(false) }

    if (isFilterDialogVisible) {
        FilterDialog(
            selectedTags = selectedTags,
            onAddFilter = { onAddFilter(it) },
            onRemoveFilter = { onRemoveFilter(it) },
            onDismiss = {
                isFilterDialogVisible = false
                applyFilters()
            }
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.clickable { isFilterDialogVisible = true },
            painter = painterResource(id = R.drawable.filter_icon),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .weight(1f),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null
        )
        Icon(
            modifier = Modifier.clickable { navController.navigate(route = Route.SearchScreen.route) },
            painter = painterResource(id = R.drawable.search_icon),
            contentDescription = null
        )
    }
}
