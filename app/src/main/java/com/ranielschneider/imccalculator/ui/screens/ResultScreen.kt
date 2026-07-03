package com.ranielschneider.imccalculator.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ranielschneider.imccalculator.ui.theme.AppDarkBg

@Composable
fun ResultScreen(imc: String?) {
    val imcDouble = imc?.toDoubleOrNull()
    val imcFormatado = "%.2f".format(imcDouble ?: 0.0)

    val (classificacao, cor) = when {
        imcDouble == null -> Pair("Inválido", Color.Gray)
        imcDouble < 18.5 -> Pair("Abaixo do peso", Color(0xFF3B82F6))
        imcDouble < 25.0 -> Pair("Peso normal", Color(0xFF10B981))
        imcDouble < 30.0 -> Pair("Sobrepeso", Color(0xFFF59E0B))
        else -> Pair("Obesidade", Color(0xFFEF4444))
    }

    // Converte IMC pra posição na barra (IMC 15 a 40)
    val imcMin = 15f
    val imcMax = 40f
    val posicaoAlvo = ((( imcDouble?.toFloat() ?: imcMin) - imcMin) / (imcMax - imcMin)).coerceIn(0f, 1f)

    var animacaoIniciada by remember { mutableStateOf(false) }
    val posicaoAnimada by animateFloatAsState(
        targetValue = if (animacaoIniciada) posicaoAlvo else 0f,
        animationSpec = tween(durationMillis = 1200),
        label = "indicador_imc"
    )

    LaunchedEffect(Unit) {
        animacaoIniciada = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppDarkBg),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "Seu resultado",
                fontSize = 20.sp,
                color = Color.White.copy(alpha = 0.6f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(
                        brush = Brush.radialGradient(
                            listOf(cor.copy(alpha = 0.3f), Color.Transparent)
                        ),
                        shape = RoundedCornerShape(100.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = imcFormatado,
                        fontSize = 56.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )
                    Text(
                        text = "IMC",
                        fontSize = 16.sp,
                        color = Color.White.copy(alpha = 0.5f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .background(
                        color = cor.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .padding(horizontal = 32.dp, vertical = 12.dp)
            ) {
                Text(
                    text = classificacao,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = cor
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Barra de classificação
            Text(
                text = "Escala de IMC",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.5f),
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                // Barra colorida
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    Color(0xFF3B82F6),
                                    Color(0xFF10B981),
                                    Color(0xFFF59E0B),
                                    Color(0xFFEF4444)
                                )
                            )
                        )
                )

                // Indicador deslizante
                Box(
                    modifier = Modifier
                        .fillMaxWidth(posicaoAnimada)
                        .wrapContentWidth(Alignment.End)
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .offset(y = (-4).dp)
                            .background(Color.White, RoundedCornerShape(10.dp))
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Labels da barra
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "15", fontSize = 11.sp, color = Color.White.copy(alpha = 0.4f))
                Text(text = "18.5", fontSize = 11.sp, color = Color.White.copy(alpha = 0.4f))
                Text(text = "25", fontSize = 11.sp, color = Color.White.copy(alpha = 0.4f))
                Text(text = "30", fontSize = 11.sp, color = Color.White.copy(alpha = 0.4f))
                Text(text = "40+", fontSize = 11.sp, color = Color.White.copy(alpha = 0.4f))
            }
        }
    }
}