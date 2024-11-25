package com.foodies.catalog_feature.utils

import com.foodies.core_ui.model.Category
import com.foodies.core_ui.model.Product
import com.foodies.core_ui.model.TagInApp
import com.foodies.catalog_feature.model.CategoryCatalog
import com.foodies.catalog_feature.model.ProductCatalog
import com.foodies.catalog_feature.model.TagInAppCatalog

fun TagInAppCatalog.toTagInApp() = TagInApp(
    id = id,
    name = name,
    imageRes = imageRes
)

fun TagInApp.toTagInAppCatalog() = TagInAppCatalog(
    id = id,
    name = name,
    imageRes = imageRes
)




fun CategoryCatalog.toCategory() = Category(
    id = id,
    name = name,
)

fun Category.toCategoryCatalog() = CategoryCatalog(
    id = id,
    name = name,
)




fun ProductCatalog.toProduct() = Product(
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


fun Product.toProductCatalog() = ProductCatalog(
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