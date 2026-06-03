package com.example.hairbook.feature.profile.ui

sealed interface ProfileUiEvent {
    data object EditProfileClicked : ProfileUiEvent
}
