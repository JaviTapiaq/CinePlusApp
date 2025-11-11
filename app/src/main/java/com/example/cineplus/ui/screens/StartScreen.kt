package com.example.cineplus.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cineplus.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(onNavigateToLogin: () -> Unit) {
    val scope = rememberCoroutineScope()
    var showContent by remember { mutableStateOf(false) }
    val alphaAnim = remember { Animatable(0f) }

    // 🔹 Efecto de aparición animada
    LaunchedEffect(Unit) {
        alphaAnim.animateTo(1f, animationSpec = tween(durationMillis = 1500))
        delay(500)
        showContent = true
    }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.alpha(alphaAnim.value)
            ) {
                // 🔹 Logo (usa tu recurso si tienes uno en /res/drawable)
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Logo CinePlus",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(160.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "CinePlus SPA 🎬",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Tu experiencia de cine y streaming en un solo lugar",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 32.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(40.dp))

                AnimatedVisibility(visible = showContent) {
                    Button(
                        onClick = {
                            scope.launch {
                                alphaAnim.animateTo(0f, animationSpec = tween(800))
                                delay(300)
                                onNavigateToLogin()
                            }
                        },
                        modifier = Modifier
                            .width(220.dp)
                            .height(56.dp),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Text("Comenzar", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}
