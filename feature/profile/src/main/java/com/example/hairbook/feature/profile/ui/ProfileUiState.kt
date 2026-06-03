package com.example.hairbook.feature.profile.ui

import com.example.hairbook.feature.profile.domain.model.Profile

data class ProfileUiState(
    val isLoading: Boolean = false,
    val profile: Profile? = null,
    val errorMessage: String? = null
)
