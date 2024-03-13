package com.ucne.parcial2_kissland_ap2.ui.theme.consulta

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ucne.parcial2_kissland_ap2.data.entity.SistemaEntity
import com.ucne.parcial2_kissland_ap2.ui.theme.registro.SistemaEvent
import com.ucne.parcial2_kissland_ap2.ui.theme.registro.SistemaViewModel

@Composable
fun Consulta(
    viewModel: SistemaViewModel = hiltViewModel(),
    navController: NavController
) {
    val sistema by viewModel.sistema.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "Consulta de Sistemas",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.Gray
        )

        LazyColumn {
            items(sistema) { sistema ->
                ExpandableCard(
                    sistema = sistema,
                    onDeleteClick = {
                        viewModel.onEvent(SistemaEvent.onDelete(sistema))
                    },
                    onNavigateToSistemaScreen = {
                        navController.navigate("registro/${sistema.idSistema}")
                    }
                )
            }
        }
    }
}

@Composable
fun ExpandableCard(
    sistema: SistemaEntity,
    onDeleteClick: () -> Unit,
    onNavigateToSistemaScreen: (SistemaEntity) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)) {
                                append("ID: ")
                            }
                            withStyle(style = SpanStyle(color = Color.LightGray)) {
                                append("${sistema.idSistema}")
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)) {
                                append("Nombre: ")
                            }
                            withStyle(style = SpanStyle(color = Color.LightGray)) {
                                append(sistema.nombre)
                            }
                        }
                    )
                }
                IconButton(
                    onClick = {
                        onNavigateToSistemaScreen(sistema)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        tint = Color.Yellow,
                        contentDescription = "Ver detalles"
                    )
                }
                IconButton(
                    onClick = {
                        showDialog = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        tint = Color.Red,
                        contentDescription = "Eliminar"
                    )
                }
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            showDialog = false
                        },
                        icon = { Icon(Icons.Default.Warning, contentDescription = null) },
                        title = {
                            Text(text = "Eliminar Sistema")
                        },
                        text = {
                            Text("¿Estás seguro de que quieres eliminar este sistema?  " + "Esta acción no se puede deshacer.")
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    onDeleteClick()
                                    showDialog = false
                                }
                            ) {
                                Text("Eliminar")
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    showDialog = false
                                }
                            ) {
                                Text("Cancelar")
                            }
                        }
                    )
                }
            }
        }
    }
}