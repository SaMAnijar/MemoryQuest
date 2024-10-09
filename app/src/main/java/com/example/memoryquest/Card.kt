package com.example.memoryquest

data class Card(
    val imageResource: Int,
    var isFaceUp: Boolean = false,
    var isMatched: Boolean = false
)
