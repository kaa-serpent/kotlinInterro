package com.example.chucknoris.api

import okhttp3.OkHttpClient

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()
/*
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.icndb.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }*/
}