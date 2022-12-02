package com.treetrack.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    var mHttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    var mOkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(mHttpLoggingInterceptor)
        .build()

    val apiClient: ApiActions by lazy {
        Retrofit.Builder()
            .baseUrl("http://tracker-3track.a2hosted.com")
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiActions::class.java)
    }
}