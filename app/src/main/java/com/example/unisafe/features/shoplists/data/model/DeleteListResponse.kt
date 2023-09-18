package com.example.unisafe.features.shoplists.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeleteListResponse(
    val success: Boolean,
    @Json(name = "new_value")
    val newValue: Boolean
)