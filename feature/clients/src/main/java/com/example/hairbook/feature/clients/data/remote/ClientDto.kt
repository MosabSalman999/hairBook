package com.example.hairbook.feature.clients.data.remote

data class ClientDto(
    val id: String,
    val name: String,
    val phone: String?,
    val email: String?,
    val notes: String?
)
