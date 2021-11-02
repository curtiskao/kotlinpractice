package com.example.kotlinpractice.Crypto

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoApiInterface {

    @GET("coins")
    fun getCrypto() : Call<List<CoinDto>>

    @GET("coins/{coin_id}")
    fun getCryptoDetails(@Path("coin_id") coin_id: String): Call<CoinDetailDto>
}