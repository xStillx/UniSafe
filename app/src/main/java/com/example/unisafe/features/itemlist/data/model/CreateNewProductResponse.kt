package com.example.unisafe.features.itemlist.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateNewProductResponse(
    val success: Boolean,
    @Json(name = "item_id")
    val itemId: Int
)