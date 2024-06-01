package com.uleam.notificacion.ui.vistas

import com.uleam.notificacion.R


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.uleam.notificacion.data.Vistas.Vistas
import com.uleam.notificacion.ui.AppViewModelProvider
import com.uleam.notificacion.ui.navigation.NavigationController


object VistasDestination : NavigationController {
    override val route = "vistas"
    override val titleRes = R.string.vistas_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VistasScreen(
    vistaId: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: VistasViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val vistaUiState by viewModel.vistaUiState.collectAsState()
    val vistasFiltradas = vistaUiState.vistas.filter { it.NotificacionID == vistaId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Vista Detallada") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            when {
                vistaUiState.isLoading -> {
                    LoadingState()
                }
                vistaUiState.errorMessage != null -> {
                    ErrorState(errorMessage = vistaUiState.errorMessage!!)
                }
                else -> {
                    vistasFiltradas.forEach { vista ->
                        VistaData(vista)
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingState() {
    Text(
        text = "Cargando...",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    )
}

@Composable
fun ErrorState(errorMessage: String) {
    Text(
        text = "Error: $errorMessage",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    )
}

@Composable
fun VistaData(vista: Vistas) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color(0xFFFFF0C2)),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "ID: ${vista.VistasID}")
            Text(text = "NotificacionId: ${vista.NotificacionID}")
            Text(text = "Usuario: ${vista.Usuario}")
            Text(text = "Fecha: ${vista.FechaVista}")
        }
    }
}
