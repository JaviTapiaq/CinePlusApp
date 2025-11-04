package com.example.cineplus.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Perfil de usuario", style = androidx.compose.material3.MaterialTheme.typography.titleLarge)
        Text("Historial de compras (demo)", modifier = Modifier.padding(top = 8.dp))
    }
}