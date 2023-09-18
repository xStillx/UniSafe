package com.example.unisafe.features.itemlist.data.model.mapper

import com.example.unisafe.features.itemlist.data.model.DeleteProductResponse
import com.example.unisafe.features.itemlist.domain.model.DeleteProductAnswer
import javax.inject.Inject

class DeleteProductMapper @Inject constructor() {

    fun map(deleteProductResponse: DeleteProductResponse) = DeleteProductAnswer(
        success = deleteProductResponse.success,
        rowsAffected = deleteProductResponse.rowsAffected
    )
}