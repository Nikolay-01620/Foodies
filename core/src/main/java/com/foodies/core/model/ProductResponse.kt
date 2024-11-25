package com.foodies.core.model

import com.google.gson.annotations.SerializedName


/** Этот код определяет класс данных Product с помощью языка программирования Kotlin.
Он используется для хранения информации о продуктах,
таких как идентификатор продукта, его категория, название,
описание, изображение, текущая и старая цена, единицы измерения,
пищевая ценность и теги.

Аннотация @SerializedName используется в библиотеке Gson для
указания соответствия имени поля в классе данных Kotlin и имени
поля в JSON-объекте при десериализации (преобразовании JSON в объект Kotlin)
и сериализации (преобразовании объекта Kotlin в JSON).

В Kotlin, как и во многих других языках программирования,
имена полей могут отличаться от тех, что используются в JSON-данных.
Например, в JSON может быть поле "first_name", а в Kotlin вы бы хотели
использовать имя firstName. */
data class ProductResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("category_id") val categoryId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
    @SerializedName("price_current") val priceCurrent: Int,
    @SerializedName("price_old") val priceOld: Int?,
    @SerializedName("measure") val measure: Int,
    @SerializedName("measure_unit") val measureUnit: String,
    @SerializedName("energy_per_100_grams") val energyPer100Grams: Double,
    @SerializedName("proteins_per_100_grams") val proteinsPer100Grams: Double,
    @SerializedName("fats_per_100_grams") val fatsPer100Grams: Double,
    @SerializedName("carbohydrates_per_100_grams") val carbohydratesPer100Grams: Double,
    @SerializedName("tag_ids") val tagIds: List<Int>,
    val count: Int = 0

)