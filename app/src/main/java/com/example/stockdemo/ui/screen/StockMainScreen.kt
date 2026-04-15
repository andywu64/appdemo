package com.example.stockdemo.ui.screen

import android.graphics.Color
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.pullToRefreshIndicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stockdemo.ui.component.StockTopBar
import com.example.stockdemo.ui.theme.Transparent
import com.example.stockdemo.util.networkCallbackChange
import com.example.stockdemo.viewmodel.StockAllViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockMainScreen(
    viewModel: StockAllViewModel = viewModel(),
) {
    val isRefreshing by viewModel.refreshState.collectAsState()
    val state = rememberPullToRefreshState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        networkCallbackChange(context = context, onConnection = {
            viewModel.setInternetConnectionState(it)
            if (it) {
                viewModel.onRefresh()
            }
        })
    }
    /*DisposableEffect(Unit) {
        onDispose {
            networkCallbackChange(context = context, registerState = false) {

            }
        }
    }*/

    Scaffold(
        topBar = { StockTopBar(
            onClickMean = {
                viewModel.setShowSortTypeState(true)
            }
        ) },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize())
    { innerPadding ->
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = viewModel::onRefresh,
            modifier = Modifier.padding(innerPadding),
            state = state,
            indicator = {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .pullToRefreshIndicator(
                            state = state,
                            isRefreshing = isRefreshing,
                            containerColor = Transparent,
                            threshold = 80.dp,
                            ),
                    contentAlignment = Alignment.Center
                ) {
                    Crossfade(
                        targetState = isRefreshing,
                        animationSpec = tween(durationMillis = 100)
                    ) { refreshing ->
                        val distanceFraction = { state.distanceFraction.coerceIn(0f, 1f) }
                        if (refreshing) {
                            CircularProgressIndicator()
                        } else {
                            CircularProgressIndicator(
                                progress = distanceFraction,
                                color = MaterialTheme.colorScheme.secondary,
                            )
                        }
                    }
                }
            },
        ) {
            StockAllScreen(
                viewModel = viewModel,
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = Color.WHITE.toLong()
)
@Composable
fun StockMainScreenPreview() {
    StockMainScreen()
}