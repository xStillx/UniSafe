package com.example.unisafe.features.itemlist.data.model.mapper

import com.example.unisafe.features.itemlist.data.model.CreateNewProductResponse
import com.example.unisafe.features.shoplists.domain.model.CreateNewListAnswer
import javax.inject.Inject

class CreateNewProductMapper @Inject constructor() {

    fun map(createNewListResponse: CreateNewProductResponse) = CreateNewListAnswer(
        success = createNewListResponse.success,
        listId = createNewListResponse.itemId
    )
}