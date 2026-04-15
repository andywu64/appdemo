package com.example.stockdemo.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stockdemo.R
import com.example.stockdemo.viewmodel.StockAllViewModel

@Composable
fun BottomSheetItem(title: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun BottomSheetItemPreview(
) {
    val viewModel : StockAllViewModel = viewModel()
    viewModel.setPreviewStockAll()
    BottomSheetItem(
        title = stringResource(R.string.sorted_by_code_ascending)
    ) {

    }
}