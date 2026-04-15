package com.example.stockdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.ui.platform.ComposeView
import com.example.stockdemo.ui.screen.StockMainScreen
import com.example.stockdemo.ui.theme.StockTheme
import com.example.stockdemo.viewmodel.StockAllViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: StockAllViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        findViewById<ComposeView>(R.id.composeView).setContent {
            StockTheme {
                StockMainScreen(viewModel = viewModel)
            }
        }
    }
}
