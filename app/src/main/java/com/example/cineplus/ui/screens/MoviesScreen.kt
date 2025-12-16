package com.example.cineplus.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.cineplus.viewmodel.MoviesViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(onBack: () -> Unit) {

    val context = LocalContext.current

    val viewModel: MoviesViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MoviesViewModel(context) as T
            }
        }
    )

    LaunchedEffect(Unit) {
        viewModel.loadMovies()
    }

    //  stateflow
    val search by viewModel.search.collectAsState()
    val movies by viewModel.movies.collectAsState()

    // filtrado
    val filteredMovies = remember(search, movies) {
        if (search.isBlank()) {
            movies
        } else {
            movies.filter {
                it.titulo.contains(search, ignoreCase = true)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Películas en cartelera") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = search,
                onValueChange = viewModel::updateSearch,
                label = { Text("Buscar película...") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            // lista
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredMovies.size) { index ->
                    val m = filteredMovies[index]

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(12.dp)
                                .height(IntrinsicSize.Min)
                        ) {

                            // imagen
                            AsyncImage(
                                model = m.imagen.url,
                                contentDescription = m.titulo,
                                modifier = Modifier
                                    .width(110.dp)
                                    .fillMaxHeight()
                                    .aspectRatio(2f / 3f)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            // info
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {

                                Column {
                                    Text(
                                        text = m.titulo,
                                        style = MaterialTheme.typography.titleMedium
                                    )

                                    Spacer(Modifier.height(4.dp))

                                    Text(
                                        text = m.categoria,
                                        style = MaterialTheme.typography.bodySmall
                                    )

                                    Spacer(Modifier.height(8.dp))

                                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                        LabelBox(text = m.restriccionEdad)
                                        LabelBox(text = "${m.duracion} min")
                                    }
                                }

                                Spacer(Modifier.height(8.dp))

                                // horarios
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    modifier = Modifier.wrapContentHeight()
                                ) {
                                    m.horarios.take(3).forEach { h ->
                                        AssistChip(
                                            onClick = { /* ir a pago */ },
                                            label = { Text(h) }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LabelBox(text: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
    ) {
        Text(text, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
    }
}
