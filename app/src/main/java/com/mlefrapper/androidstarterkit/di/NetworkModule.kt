package com.mlefrapper.androidstarterkit.di

import com.mlefrapper.androidstarterkit.BuildConfig
import com.mlefrapper.androidstarterkit.data.remote.ApiService
import com.mlefrapper.androidstarterkit.data.remote.AuthInterceptor
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .addInterceptor(
                    interceptor = AuthInterceptor(),
                )
                .addInterceptor(
                    interceptor = HttpLoggingInterceptor()
                        .setLevel(
                            level = HttpLoggingInterceptor.Level.BODY,
                        ),
                )
                .connectTimeout(
                    timeout = TIMEOUT_DURATION,
                    unit = TimeUnit.SECONDS,
                )
                .readTimeout(
                    timeout = TIMEOUT_DURATION,
                    unit = TimeUnit.SECONDS,
                )
                .build()
        } else {
            OkHttpClient.Builder()
                .addInterceptor(
                    interceptor = AuthInterceptor(),
                )
                .connectTimeout(
                    timeout = TIMEOUT_DURATION,
                    unit = TimeUnit.SECONDS,
                )
                .readTimeout(
                    timeout = TIMEOUT_DURATION,
                    unit = TimeUnit.SECONDS,
                )
                .build()
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    private const val TIMEOUT_DURATION = 120L
}
