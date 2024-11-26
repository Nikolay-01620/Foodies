package com.foodies.details_feature.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.foodies.core_ui.route.Route
import com.foodies.details_feature.model.ProductDetails
import com.foodies.feature_details.R
import com.foodies.details_feature.presentation.components.AppButton
import com.foodies.details_feature.presentation.components.Description
import com.foodies.details_feature.presentation.components.ItemDescription
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@Composable
fun DetailsScreen(
    navController: NavController,
    viewModelProvider: ViewModelProvider.Factory
) {

    val detailsViewModel: DetailsViewModel = viewModel(
        factory = viewModelProvider
    )
    val descriptions = listOf(
        Description.WEIGHT,
        Description.ENERGY_VALUE,
        Description.PROTEINS,
        Description.FATS,
        Description.CARBOHYDRATES,
    )

    val type: Type = object : TypeToken<ProductDetails>() {}.type

    val json = Gson()
    val product: ProductDetails? =
        json.fromJson(navController.currentBackStackEntry?.arguments?.getString("product"), type)
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
            ) {

                Image(painter = painterResource(id = R.drawable.photo), contentDescription = null)
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = product?.name ?: "",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.W400
                )
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = product?.description ?: "",
                    color = Color.Gray
                )
                repeat(descriptions.size) {
                    val itemDescription = descriptions[it]

                    val itemValue = when (itemDescription) {
                        Description.WEIGHT -> stringResource(
                            id = R.string.fats_measurements,
                            product?.measure.toString()
                        )

                        Description.ENERGY_VALUE -> stringResource(
                            id = R.string.energy_value_measurements,
                            product?.energyPer100Grams.toString()
                        )

                        Description.PROTEINS -> stringResource(
                            id = R.string.fats_measurements,
                            product?.proteinsPer100Grams.toString()
                        )

                        Description.FATS -> stringResource(
                            id = R.string.fats_measurements,
                            product?.fatsPer100Grams.toString()
                        )

                        Description.CARBOHYDRATES -> stringResource(
                            id = R.string.fats_measurements,
                            product?.carbohydratesPer100Grams.toString()
                        )
                    }
                    ItemDescription(
                        text = stringResource(id = itemDescription.textRes),
                        value = itemValue
                    )
                }
            }
            AppButton(
                onButtonClick = {
                    detailsViewModel.addItem(product ?: return@AppButton)
                    navController.navigate(
                        route =
                        Route.BasketScreen.route
                    )
                }, text = stringResource(
                    id = R.string.in_cart_button_label,
                    product?.priceCurrent.toString()
                )
            )
        }
        Icon(
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    navController.popBackStack()
                },
            painter = painterResource(id = R.drawable.arrow_left_icon),
            contentDescription = null
        )
    }
}
