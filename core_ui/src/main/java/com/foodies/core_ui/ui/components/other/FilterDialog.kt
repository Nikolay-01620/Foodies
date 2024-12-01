package com.foodies.core_ui.ui.components.other

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.foodies.core_ui.R
import com.foodies.core_ui.model.TagInApp
import com.foodies.core_ui.ui.Grey2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterDialog(
    selectedTags: List<TagInApp>,
    onAddFilter: (TagInApp) -> Unit,
    onRemoveFilter: (TagInApp) -> Unit,
    onDismiss: () -> Unit,
) {
    val dialogState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        sheetState = dialogState,
        onDismissRequest = onDismiss
    ) {
        FilterDialogContent(
            selectedFilters = selectedTags,
            onAddFilter = onAddFilter,
            onRemoveFilter = onRemoveFilter,
            onDismiss = onDismiss
        )
    }
}

@Composable
private fun FilterDialogContent(
    selectedFilters: List<TagInApp>,
    onAddFilter: (TagInApp) -> Unit,
    onRemoveFilter: (TagInApp) -> Unit,
    onDismiss: () -> Unit,
) {
    val filtersTag = TagInApp.getList()

    Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp)) {
        Text(text = "Подобрать блюда")
        repeat(filtersTag.size) {
            val tag = filtersTag[it]
            val isChecked = selectedFilters.find { it.id == tag.id } != null
            FilterItem(
                tagInApp = tag,
                checked = isChecked,
                onCheckedValueChange = { checked ->
                    if (checked) {
                        onAddFilter(tag)
                    } else onRemoveFilter(tag)
                }
            )
        }
        AppButton(
            onButtonClick = onDismiss,
            text = stringResource(id = R.string.done_button_label)
        )
    }
}

@Composable
private fun FilterItem(
    tagInApp: TagInApp,
    checked: Boolean,
    onCheckedValueChange: (Boolean) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = tagInApp.name)
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedValueChange
            )
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .background(Grey2)
            .fillMaxWidth())
    }
}