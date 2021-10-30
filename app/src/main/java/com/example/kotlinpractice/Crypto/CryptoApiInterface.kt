package com.example.kotlinpractice.Crypto

import retrofit2.Call
import retrofit2.http.GET

interface CryptoApiInterface {

    @GET("coins")
    fun getCrypto() : Call<List<CoinDto>>
}