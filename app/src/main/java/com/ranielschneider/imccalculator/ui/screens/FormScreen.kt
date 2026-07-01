package com.ranielschneider.imccalculator.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

val Purple = Color(0xFF7C3AED)
val Pink = Color(0xFFEC4899)
val DarkBg = Color(0xFF0F0F1A)
val DarkSurface = Color(0xFF1A1A2E)

@Composable
fun FormScreen(modifier: Modifier = Modifier, navController: NavController) {
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf("") }
    var botaoPressionado by remember { mutableStateOf(false) }

    val escala by animateFloatAsState(
        targetValue = if (botaoPressionado) 0.95f else 1f,
        animationSpec = tween(100),
        label = "escala_botao"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Calculadora",
                fontSize = 36.sp,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
            Text(
                text = "de IMC",
                fontSize = 36.sp,
                fontWeight = FontWeight.Black,
                color = Pink
            )

            Spacer(modifier = Modifier.height(48.dp))

            OutlinedTextField(
                value = peso,
                onValueChange = { peso = it },
                label = { Text("Peso (kg)") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Purple,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                    focusedLabelColor = Purple,
                    unfocusedLabelColor = Color.White.copy(alpha = 0.5f),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Purple
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = altura,
                onValueChange = { altura = it },
                label = { Text("Altura (m)") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Purple,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                    focusedLabelColor = Purple,
                    unfocusedLabelColor = Color.White.copy(alpha = 0.5f),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Purple
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (erro.isNotEmpty()) {
                Text(
                    text = erro,
                    color = Pink,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(escala)
                    .background(
                        brush = Brush.horizontalGradient(listOf(Purple, Pink)),
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Button(
                    onClick = {
                        botaoPressionado = true
                        val pesoNum = peso.toDoubleOrNull()
                        val alturaNum = altura.toDoubleOrNull()
                        if (pesoNum == null || alturaNum == null) {
                            erro = "Preencha corretamente. Ex: peso: 70.5 | altura: 1.75"
                            botaoPressionado = false
                        } else {
                            val imc = pesoNum / (alturaNum * alturaNum)
                            navController.navigate("resultado/$imc")
                            botaoPressionado = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Calcular",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}