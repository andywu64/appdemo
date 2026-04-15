package com.example.stockdemo.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stockdemo.R
import com.example.stockdemo.ui.StockSortType
import com.example.stockdemo.ui.component.BottomSheetItem
import com.example.stockdemo.ui.component.ShowStockBWIBBU
import com.example.stockdemo.ui.component.StockItem
import com.example.stockdemo.ui.theme.Red
import com.example.stockdemo.viewmodel.StockAllViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockAllScreen(
    modifier: Modifier = Modifier,
    viewModel: StockAllViewModel,
) {
    val stockAllItems by viewModel.stockAllState.collectAsState()
    val showSortType by viewModel.showSortTypeState.collectAsState()
    val sortType by viewModel.sortTypeState.collectAsState()
    val connectionState by viewModel.internetConnectionState.collectAsState()

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showStockInfo by remember { mutableStateOf(false) }

    val avgMap = remember(viewModel.stockAvgAllState.collectAsState().value) {
        viewModel.stockAvgAllState.value.associateBy { it.code }
    }

    val infoMap = remember(viewModel.stockBWIBBUAllState.collectAsState().value) {
        viewModel.stockBWIBBUAllState.value.associateBy { it.code }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        if (!connectionState) {
            item {
                Text(
                    text = stringResource(R.string.disconnected),
                    fontSize = 24.sp,
                    color = Red,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

        items(
            items = stockAllItems,
            key = { it.code } // ⭐ performance improvement
        ) { item ->
            StockItem(
                viewModel = viewModel,
                item = item,
                monthlyAvg = avgMap[item.code]?.monthlyAveragePrice ?: "-",
                info = infoMap.containsKey(item.code)
            ) {
                viewModel.setStockCodeState(item.code)
                showStockInfo = true
            }
        }
    }

    if (showStockInfo) {
        ShowStockBWIBBU(
            viewModel = viewModel,
            onConfirmation = { showStockInfo = false },
            onDismissRequest = { showStockInfo = false }
        )
    }

    if (showSortType) {
        ModalBottomSheet(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.tertiary,
            onDismissRequest = {
                viewModel.setShowSortTypeState(false)
            },
            sheetState = sheetState
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
            ){
                items(StockSortType.entries) { type ->
                    BottomSheetItem(
                        title = stringResource(type.title)
                    ) {
                        if (sortType != type) {
                            viewModel.setSortTypeState(type)
                            viewModel.onRefresh()
                        }
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                viewModel.setShowSortTypeState(false)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun StockAllScreenPreview(
) {
    val viewModel : StockAllViewModel = viewModel()
    viewModel.setPreviewStockAll()
    StockAllScreen(
        viewModel = viewModel,
    )
}
