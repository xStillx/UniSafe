package com.example.unisafe.features.itemlist.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeleteProductResponse(
    val success: Boolean,
    @Json(name = "rows_affected")
    val rowsAffected: Int
)