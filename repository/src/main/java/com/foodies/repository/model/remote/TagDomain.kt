package com.foodies.repository.model.remote

import com.google.gson.annotations.SerializedName


/** Этот класс позволяет удобно представлять информацию о тегах,
которые могут быть связаны с продуктами. Например,
если у продукта есть несколько тегов
(например, "акция", "новинка", "скидка")
каждый тег будет представлен объектом Tag
содержащим его id и name. */
data class TagDomain(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)
