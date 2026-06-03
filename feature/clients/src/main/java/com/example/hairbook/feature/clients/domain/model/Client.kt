package com.example.hairbook.feature.clients.domain.model

data class Client(
    val id: String,
    val name: String,
    val phone: String?,
    val email: String?,
    val notes: String?
)
