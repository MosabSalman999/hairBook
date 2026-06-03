package com.example.hairbook.core.network

data class NetworkError(
    val message: String,
    val code: Int? = null,
    val cause: Throwable? = null
)
