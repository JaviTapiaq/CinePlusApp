package com.example.cineplus.data.local

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


object TokenStore {
    @Volatile
    var token: String? = null
}

class AuthInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val token = TokenStore.token
        return if (token.isNullOrEmpty()) {
            chain.proceed(original)
        } else {
            val request = original.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(request)
        }
    }
}