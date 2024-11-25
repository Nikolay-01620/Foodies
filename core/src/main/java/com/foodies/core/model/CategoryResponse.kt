package com.foodies.core.model

import com.google.gson.annotations.SerializedName


/**  Класс данных Category используется для хранения
информации о категориях товаров, таких как идентификатор
категории и ее название. Аннотации @SerializedName используются для указания имен полей
в JSON-ответе, которые будут сопоставлены с полями класса.

Вот что он делает:

@SerializedName("id") val id: Int: Поле id типа Int,
которое будет сопоставлено с полем id в JSON-ответе.

@SerializedName("name") val name: String: Поле name типа String,
которое будет сопоставлено с полем name в JSON-ответе.*/
data class CategoryResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)
