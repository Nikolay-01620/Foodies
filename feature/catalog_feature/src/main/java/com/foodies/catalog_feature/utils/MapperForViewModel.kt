package com.foodies.catalog_feature.utils

import com.foodies.core_ui.model.Tag
import com.foodies.catalog_feature.model.CategoryCatalog
import com.foodies.catalog_feature.model.ProductCatalog
import com.foodies.catalog_feature.model.TagCatalog
import com.foodies.repository.model.remote.CategoryDomain
import com.foodies.repository.model.remote.ProductDomain
import com.foodies.repository.model.remote.TagDomain

fun CategoryDomain.toCategoryCatalog() = CategoryCatalog(
    id = id,
    name = name
)

fun ProductDomain.toProductCatalog() = ProductCatalog(
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


fun ProductCatalog.toProductDomain() = ProductDomain(
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

fun TagDomain.toTag() = Tag(
    id = id,
    name = name
)




