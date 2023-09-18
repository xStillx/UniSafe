package com.example.unisafe.features.itemlist.di

import com.example.unisafe.features.itemlist.data.api.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class ItemLisFeatureModule {

    @Provides
    fun provideShopListsApi(retrofit: Retrofit) = retrofit.create(
        ProductApi::class.java
    )
}