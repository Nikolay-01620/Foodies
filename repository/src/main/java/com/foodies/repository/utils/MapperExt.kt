package com.foodies.repository.utils

import com.foodies.core.model.CategoryResponse
import com.foodies.core.model.ProductResponse
import com.foodies.core.model.TagResponse
import com.foodies.repository.model.remote.CategoryDomain
import com.foodies.repository.model.remote.ProductDomain
import com.foodies.repository.model.remote.TagDomain

fun CategoryResponse.toCategoryDomain() = CategoryDomain(
    id = id,
    name = name
)
fun ProductResponse.toProductDomain() = ProductDomain(
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
fun ProductDomain.toProductResponse() = ProductResponse(
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
fun TagResponse.toTagDomain() = TagDomain(
    id = id,
    name = name
)

