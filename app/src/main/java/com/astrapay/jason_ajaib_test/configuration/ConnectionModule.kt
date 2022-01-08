package com.astrapay.jason_ajaib_test.configuration

import com.astrapay.jason_ajaib_test.helper.DefaultConstants
import com.astrapay.jason_ajaib_test.helper.Logger
import com.astrapay.jason_ajaib_test.helper.exception.BadRequestException
import com.astrapay.jason_ajaib_test.helper.exception.ServerFailedException
import com.astrapay.jason_ajaib_test.helper.exception.UnAuthorizedException
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object ConnectionModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ConnectionInterceptor

    @ConnectionInterceptor
    @Provides
    fun provideConnectionRequestInterceptor(
    ) = Interceptor {
        var request = it.request()
        val requestBuilder = request.newBuilder()

        val accept = "application/vnd.github.v3+json"
        Logger.d("[accept] $accept")
        val authorizationToken = DefaultConstants.OAuth
        Logger.d("[authorization-token] Bearer $authorizationToken")

        requestBuilder.addHeader("Accept", accept)
        requestBuilder.addHeader("Authorization", "Bearer $authorizationToken")

        request = requestBuilder.build()
        val response = it.proceed(request)
        when (response.code()) {
            400 -> throw BadRequestException("Bad Request")
            401, 403 -> throw UnAuthorizedException("Unauthorized user.")
            in 500..550 -> throw ServerFailedException("Server error.")
        }
        response
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ApiConnection

    @ApiConnection
    @Provides
    fun provideConnectionRetrofit(
        @ConnectionInterceptor requestInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): Retrofit {

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(requestInterceptor)
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}