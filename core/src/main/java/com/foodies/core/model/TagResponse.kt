package com.foodies.core.model

import com.google.gson.annotations.SerializedName

data class TagResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)
