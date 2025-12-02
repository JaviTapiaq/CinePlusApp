package com.example.cineplus.data.remote

import android.content.Context
import com.example.cineplus.data.remote.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.example.cineplus.data.local.SessionManager
import com.example.cineplus.data.remote.api.ApiService


object RetrofitClient {

    private const val BASE_URL = "https://x8ki-letl-twmt.n7.xano.io/api:KxXEX5K_/"

    fun create(context: Context): ApiService {
        val sessionManager = SessionManager(context)

        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(sessionManager))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}





