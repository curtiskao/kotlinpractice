package com.example.kotlinpractice.Game

data class GameCard(
        var id: Int,
        var isFaceUp: Boolean = false,
        var isMatched: Boolean = false
)
