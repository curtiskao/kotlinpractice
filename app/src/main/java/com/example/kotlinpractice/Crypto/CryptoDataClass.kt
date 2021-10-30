package com.example.kotlinpractice.Crypto

data class CryptoDataClass(
        val id: String = "id",
        val symbol: String = "symbol",
        val name: String = "name",
        val rank: Int = 0,
        val isNew: Boolean = false,
        val isActive: Boolean = false,
        val type: String = "",
        val description: String = ""
)