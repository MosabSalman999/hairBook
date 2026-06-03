package com.example.hairbook.feature.gallery.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hairbook.feature.gallery.domain.usecase.GetGalleryPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    getGalleryPhotosUseCase: GetGalleryPhotosUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(GalleryUiState())
    val uiState: StateFlow<GalleryUiState> = _uiState.asStateFlow()

    init {
        getGalleryPhotosUseCase()
            .onEach { photos -> _uiState.value = _uiState.value.copy(photos = photos) }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: GalleryUiEvent) {
        when (event) {
            GalleryUiEvent.AddPhotoClicked -> Unit
        }
    }
}
