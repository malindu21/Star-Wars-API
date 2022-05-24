package com.example.starwars.models

data class Planets(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)