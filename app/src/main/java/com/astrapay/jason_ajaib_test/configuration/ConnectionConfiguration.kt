package com.astrapay.jason_ajaib_test.configuration

import com.astrapay.jason_ajaib_test.client.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ConnectionConfiguration {
    @Provides
    fun provideConnectionClient(@ConnectionModule.ApiConnection retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }
}