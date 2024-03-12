package com.ucne.parcial2_kissland_ap2.ui.theme.registro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.parcial2_kissland_ap2.data.entity.SistemaEntity
import com.ucne.parcial2_kissland_ap2.data.repository.SistemaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SistemaViewModel @Inject constructor(
    private val SistemaRepository: SistemaRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SistemaState())
    val state = _state.asStateFlow()
    val sistema: Flow<List<SistemaEntity>> = SistemaRepository.getSistema()

    fun onEvent(event: SistemaEvent) {
        when (event) {
            is SistemaEvent.Idsistema -> {
                _state.update {
                    it.copy(
                        sistema = it.sistema.copy(
                            idSistema = event.idsistema.toIntOrNull() ?: 0
                        )
                    )
                }
            }

            is SistemaEvent.Nombre -> {
                _state.update {
                    it.copy(
                        sistema = it.sistema.copy(nombre = event.nombre)
                    )
                }
            }

            SistemaEvent.onSave -> {
                val nombre = state.value.sistema.nombre

                if (nombre.isBlank()) {
                    _state.update {
                        it.copy(
                            error = "Todos los campos tienen que estar completados"
                        )
                    }
                    return
                }

                val sistema = SistemaEntity(
                    nombre = nombre,
                )

                _state.update {
                    it.copy(
                        isLoading = true,
                        error = null,
                        successMessage = null
                    )
                }

                viewModelScope.launch {
                    try {
                        SistemaRepository.guardar(sistema)
                        _state.update {
                            it.copy(
                                isLoading = false,
                                successMessage = "Se guardó correctamente"
                            )
                        }
                    } catch (e: Exception) {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = "Error al guardar: ${e.message}"
                            )
                        }
                    }
                }

                _state.update {
                    it.copy(
                        sistema = SistemaEntity()
                    )
                }
            }

            SistemaEvent.onNew -> {
                _state.update {
                    it.copy(
                        successMessage = null,
                        error = null,
                        sistema = SistemaEntity()
                    )
                }
            }

            is SistemaEvent.onDelete -> {
                viewModelScope.launch {
                    SistemaRepository.delete(event.sistema)
                }
            }
        }
    }
}

data class SistemaState(
    val isLoading: Boolean = false,
    val error: String? = "",
    val successMessage: String? = "",
    val sistema: SistemaEntity = SistemaEntity()
)

sealed interface SistemaEvent{
    data class Idsistema(val idsistema: String) : SistemaEvent
    data class Nombre(val nombre: String) : SistemaEvent
    data class onDelete(val sistema: SistemaEntity) : SistemaEvent
    object onSave : SistemaEvent
    object onNew : SistemaEvent
}