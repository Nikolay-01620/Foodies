package com.foodies.core_ui.utils

import com.foodies.core_ui.model.Product
import com.foodies.repository.model.remote.ProductDomain

fun ProductDomain.toProduct() = Product(
    id = id,
    categoryId = categoryId,
    name = name,
    description = description,
    image = image,
    priceCurrent = priceCurrent,
    priceOld = priceOld,
    measure = measure,
    measureUnit = measureUnit,
    energyPer100Grams = energyPer100Grams,
    proteinsPer100Grams = proteinsPer100Grams,
    fatsPer100Grams = fatsPer100Grams,
    carbohydratesPer100Grams = carbohydratesPer100Grams,
    tagIds = tagIds,
    count = count
)

fun Product.toProductDomain() = ProductDomain(
    id = id,
    categoryId = categoryId,
    name = name,
    description = description,
    image = image,
    priceCurrent = priceCurrent,
    priceOld = priceOld,
    measure = measure,
    measureUnit = measureUnit,
    energyPer100Grams = energyPer100Grams,
    proteinsPer100Grams = proteinsPer100Grams,
    fatsPer100Grams = fatsPer100Grams,
    carbohydratesPer100Grams = carbohydratesPer100Grams,
    tagIds = tagIds,
    count = count
)





