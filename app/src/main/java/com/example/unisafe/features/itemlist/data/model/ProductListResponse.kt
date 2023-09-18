package com.example.unisafe.features.itemlist.data.model

import com.example.unisafe.features.shoplists.data.model.ItemRes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductListResponse(
    @Json(name = "item_list")
    val itemList: List<ItemRes>,
    val success: Boolean
)
