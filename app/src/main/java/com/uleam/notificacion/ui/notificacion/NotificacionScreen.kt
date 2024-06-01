package com.uleam.notificacion.ui.notificacion

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uleam.notificacion.R
import com.uleam.notificacion.data.Notificacion.Notificacion
import com.uleam.notificacion.ui.AppViewModelProvider
import com.uleam.notificacion.ui.navigation.NavigationController


object NotificacionDestination : NavigationController {
    override val route = "notificacion"
    override val titleRes = R.string.notificacion_title
}

@Composable
fun NotificacionScreen(
    navigateToItemUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NotificacionViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val notificacionUiState by viewModel.notificacionUiState.collectAsState()

    Scaffold(
        modifier = modifier,
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            NotificacionBody(
                notificacionList = notificacionUiState.notificacionList,
                onItemClick = navigateToItemUpdate,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 16.dp)
            )
        }
    }
}

@Composable
private fun NotificacionBody(
    notificacionList: List<Notificacion>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        if (notificacionList.isEmpty()){
            NotificacionList(
                notificacionList = notificacionList,
                onItemClick = { onItemClick(it.NotificacionID) },
                contentPadding = contentPadding,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun NotificacionList(
    notificacionList: List<Notificacion>,
    onItemClick: (Notificacion) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = notificacionList, key = { it.NotificacionID }) { notificacion ->
            NotificacionInfo(
                notificacion = notificacion,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(notificacion) }
            )
        }
    }
}

@Composable
fun NotificacionInfo(
    notificacion: Notificacion,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(Color(0xFFFFF0C2)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = notificacion.imageResId),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "ID: ${notificacion.NotificacionID}",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color(0xFF6D4C41),
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = notificacion.Titulo,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF6D4C41)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notificacion.Mensaje,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF6D4C41)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notificacion.Fecha,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF6D4C41)
                )

                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}
