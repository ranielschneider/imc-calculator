package com.ranielschneider.imccalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ranielschneider.imccalculator.ui.navigation.AppNavigation
import com.ranielschneider.imccalculator.ui.theme.IMCCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMCCalculatorTheme {
                AppNavigation()
            }
        }
    }
}