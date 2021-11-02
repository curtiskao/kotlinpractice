package com.example.kotlinpractice.Crypto
import com.google.gson.annotations.SerializedName

data class CoinDetailDto(
        val id: String,
        @SerializedName("is_active")
        val isActive: Boolean,
        @SerializedName("is_new")
        val isNew: Boolean,
        val name: String,
        val rank: Int,
        val symbol: String,
        val type: String,
        val description: String
)

fun CoinDetailDto.toCryptoDataClass(): CryptoDataClass{
    return CryptoDataClass(id = id, isActive = isActive, isNew = isNew, name = name, rank = rank, symbol = symbol, type = type, description = description)
}