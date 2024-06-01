package com.uleam.notificacion.ui.vistas

import com.uleam.notificacion.data.Vistas.Vistas
import com.uleam.notificacion.data.Vistas.interfaces.VistasRepository


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class VistasUiState(
    val vistas: List<Vistas> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class VistasViewModel(
    private val vistasRepository: VistasRepository
) : ViewModel() {

    private val _vistasUiState = MutableStateFlow(VistasUiState())
    val vistaUiState: StateFlow<VistasUiState> = _vistasUiState

    init {
        viewModelScope.launch {
            vistasRepository.getAllVistas().collect { vistas ->
                _vistasUiState.value = VistasUiState(vistas = vistas)
            }
        }
    }

}
