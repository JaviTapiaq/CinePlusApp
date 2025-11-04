package com.example.cineplus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun StartScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text(text = "CinePlus", style = androidx.compose.material3.MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.navigate("login") }, modifier = Modifier.fillMaxWidth()) {
            Text("Iniciar sesión")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { navController.navigate("home") }, modifier = Modifier.fillMaxWidth()) {
            Text("Entrar como invitado")
        }
    }
}