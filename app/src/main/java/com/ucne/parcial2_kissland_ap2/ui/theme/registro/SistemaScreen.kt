package com.ucne.parcial2_kissland_ap2.ui.theme.registro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SistemaScreen(
    viewModel: SistemaViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val sistema = state.sistema

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Registro de Sistemas", style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            color = Color.Gray
        )

        OutlinedTextField(
            value = sistema.nombre,
            onValueChange = { viewModel.onEvent(SistemaEvent.Nombre(it)) },
            label = { Text(text = "Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    viewModel.onEvent(SistemaEvent.onNew)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                )
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "New")
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
            Button(
                onClick = {
                    viewModel.onEvent(SistemaEvent.onSave)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                )
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Save",)
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }
    }
}