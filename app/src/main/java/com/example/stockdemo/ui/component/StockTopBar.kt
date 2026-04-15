package com.example.stockdemo.ui.component

import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stockdemo.R
import com.example.stockdemo.viewmodel.StockAllViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockTopBar(
    onClickMean: () -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            titleContentColor = MaterialTheme.colorScheme.secondary,
        ),
        title = {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    stringResource(R.string.stock_info_list),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        },
        actions = {
            IconButton(onClick = onClickMean) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}

@Preview(
    showBackground = true,
    backgroundColor = Color.WHITE.toLong()
)
@Composable
fun StockTopBarPreview() {
    val viewModel : StockAllViewModel = viewModel()
    StockTopBar(
        onClickMean = {
            viewModel.setShowSortTypeState(true)
        }
    )
}