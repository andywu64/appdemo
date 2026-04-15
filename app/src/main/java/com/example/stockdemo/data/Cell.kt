package com.example.stockdemo.data

import androidx.compose.ui.graphics.Color
import com.example.stockdemo.ui.theme.StockTextPrimary
import com.example.stockdemo.ui.theme.StockTextSecondary

data class Cell(
    val label: String,
    val value: String,
    val labelColor: Color = StockTextSecondary,
    val valueColor: Color = StockTextPrimary
)