package com.foodies.details_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.foodies.core_ui.ui.OrangePrimary

@Composable
fun AppButton(
    onButtonClick: () -> Unit,
    text: String
) {

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(OrangePrimary, shape = RoundedCornerShape(size = 7.dp))
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .clickable {
                onButtonClick()
            },
        textAlign = TextAlign.Center,
        text = text, color = Color.White
    )
}