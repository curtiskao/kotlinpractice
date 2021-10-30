package com.example.kotlinpractice.Crypto

import com.google.gson.annotations.SerializedName

data class CoinDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)


fun CoinDto.toCryptoDataClass(): CryptoDataClass{
    return CryptoDataClass(symbol = symbol, name = name)
}
