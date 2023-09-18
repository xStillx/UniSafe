package com.example.unisafe.features.shoplists.data.model.mapper

import com.example.unisafe.features.shoplists.data.model.ItemRes
import com.example.unisafe.features.shoplists.domain.model.Item
import javax.inject.Inject

class ShopMapper @Inject constructor() {

    fun map(itemRes: ItemRes) = Item(
        created = itemRes.created,
        id = itemRes.id,
        name = itemRes.name
    )
}