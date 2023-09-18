package com.example.unisafe.features.itemlist.data.api

import com.example.unisafe.features.itemlist.data.model.CreateNewProductResponse
import com.example.unisafe.features.itemlist.data.model.DeleteProductResponse
import com.example.unisafe.features.itemlist.data.model.ProductListResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductApi {

    @POST("GetShoppingList?")
    suspend fun getProductList(@Query("list_id") id: String): ProductListResponse

    @POST("CrossItOff?")
    suspend fun deleteProduct(@Query("id") id: String): DeleteProductResponse

    @POST("AddToShoppingList?")
    suspend fun createNewProduct(
        @Query("id") id: String,
        @Query("value") value: String,
        @Query("n") count: String
    ): CreateNewProductResponse
}