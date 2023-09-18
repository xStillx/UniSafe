package com.example.unisafe.features.shoplists.data.model.mapper

import com.example.unisafe.features.shoplists.data.model.DeleteListResponse
import com.example.unisafe.features.shoplists.domain.model.DeleteListAnswer
import javax.inject.Inject

class DeleteListMapper @Inject constructor(){

    fun map(deleteListResponse: DeleteListResponse) = DeleteListAnswer(
        success = deleteListResponse.success,
        newValue = deleteListResponse.newValue
    )
}