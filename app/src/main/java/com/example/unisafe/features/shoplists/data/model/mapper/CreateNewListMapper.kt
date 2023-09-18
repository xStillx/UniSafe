package com.example.unisafe.features.shoplists.data.model.mapper

import com.example.unisafe.features.shoplists.data.model.CreateNewListResponse
import com.example.unisafe.features.shoplists.domain.model.CreateNewListAnswer
import javax.inject.Inject

class CreateNewListMapper @Inject constructor() {

    fun map(createNewListResponse: CreateNewListResponse) = CreateNewListAnswer(
        success = createNewListResponse.success,
        listId = createNewListResponse.listId
    )
}