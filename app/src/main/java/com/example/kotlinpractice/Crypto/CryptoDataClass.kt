package com.example.kotlinpractice.Crypto

data class CryptoDataClass(
        var id: String = "id",
        var symbol: String = "symbol",
        var name: String = "name",
        var rank: Int = 0,
        var isNew: Boolean = false,
        var isActive: Boolean = false,
        var type: String = "",
        var description: String = ""
)