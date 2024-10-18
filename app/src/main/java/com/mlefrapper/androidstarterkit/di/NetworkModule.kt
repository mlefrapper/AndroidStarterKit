package com.mlefrapper.androidstarterkit.di

import android.content.Context
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.mlefrapper.androidstarterkit.AndroidStarterKitApplication
import com.mlefrapper.androidstarterkit.BuildConfig
import com.mlefrapper.androidstarterkit.data.remote.ApiService
import com.mlefrapper.androidstarterkit.data.remote.AuthInterceptor
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
    ): OkHttpClient {
        val interceptor = AuthInterceptor()
        return if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .addNetworkInterceptor(
                    interceptor = FlipperOkhttpInterceptor(
                        (context as AndroidStarterKitApplication).networkFlipperPlugin,
                    ),
                )
                .addInterceptor(interceptor = interceptor)
                .addInterceptor(
                    interceptor = HttpLoggingInterceptor()
                        .setLevel(level = HttpLoggingInterceptor.Level.BODY),
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
                .addInterceptor(interceptor = interceptor)
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
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    private const val TIMEOUT_DURATION = 120L
}
