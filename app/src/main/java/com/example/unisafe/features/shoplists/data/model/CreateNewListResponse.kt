package com.example.unisafe.features.shoplists.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateNewListResponse(
    val success: Boolean,
    @Json(name = "list_id")
    val listId: Int
)