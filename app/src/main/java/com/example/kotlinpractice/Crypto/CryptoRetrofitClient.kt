package com.example.kotlinpractice.Crypto

import android.app.usage.UsageEvents.Event.NONE
import android.provider.Telephony.TextBasedSmsColumns.BODY
import com.example.kotlinpractice.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CryptoRetrofitClient {
    val retrofitClient: Retrofit.Builder by lazy {

        Retrofit.Builder()
                .baseUrl("https://api.coinpaprika.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())

    }

    val apiInterface: CryptoApiInterface by lazy {
        retrofitClient
                .build()
                .create(CryptoApiInterface::class.java)
    }
}