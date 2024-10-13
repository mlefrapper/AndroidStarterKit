package com.mlefrapper.androidstarterkit.data.remote

import com.mlefrapper.androidstarterkit.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.newBuilder()
            .addQueryParameter(
                API_KEY,
                BuildConfig.API_KEY,
            ).build()
        return chain.proceed(originalRequest.newBuilder().url(url).build())
    }

    companion object {
        private const val API_KEY = "key"
    }
}
