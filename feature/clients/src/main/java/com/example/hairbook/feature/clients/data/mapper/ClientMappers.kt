package com.example.hairbook.feature.clients.data.mapper

import com.example.hairbook.core.database.entity.ClientEntity
import com.example.hairbook.feature.clients.domain.model.Client

fun ClientEntity.toDomain(): Client = Client(id, name, phone, email, notes)

fun Client.toEntity(): ClientEntity = ClientEntity(id, name, phone, email, notes)
