package com.example.unisafe.features.itemlist.domain.model

data class DeleteProductAnswer(
    val success: Boolean,
    val rowsAffected: Int
)