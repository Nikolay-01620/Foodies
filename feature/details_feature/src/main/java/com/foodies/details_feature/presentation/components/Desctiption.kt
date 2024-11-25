package com.foodies.details_feature.presentation.components

import com.foodies.feature_details.R

enum class Description(
    val textRes: Int
) {
    WEIGHT(textRes = R.string.weight),
    ENERGY_VALUE(textRes = R.string.energy_value),
    PROTEINS(textRes = R.string.proteins),
    FATS(textRes = R.string.fats),
    CARBOHYDRATES(textRes = R.string.carbohydrates)


}