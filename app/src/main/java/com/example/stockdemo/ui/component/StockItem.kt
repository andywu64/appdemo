package com.example.stockdemo.ui.component

import android.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stockdemo.data.StockAll
import com.example.stockdemo.R
import com.example.stockdemo.data.Cell
import com.example.stockdemo.ui.theme.InfoIconColor
import com.example.stockdemo.ui.theme.StockDownColor
import com.example.stockdemo.ui.theme.StockTextPrimary
import com.example.stockdemo.ui.theme.StockTextSecondary
import com.example.stockdemo.ui.theme.StockUpColor
import com.example.stockdemo.viewmodel.StockAllViewModel
import kotlin.collections.listOf

@Composable
fun StockItem(
    viewModel: StockAllViewModel,
    item: StockAll,
    monthlyAvg: String,
    info: Boolean,
    onClick: () -> Unit,
) {
    val avgItems = viewModel.stockAvgAllState
    val textColor = MaterialTheme.colorScheme.surface

    val monthlyAvgPrice = monthlyAvg.toDoubleOrNull() ?: 0.0
    val closingPrice = item.closingPrice.toDoubleOrNull() ?: 0.0
    val openingPrice = item.openingPrice.toDoubleOrNull() ?: 0.0
    val change = item.change.toDoubleOrNull() ?: 0.0

    val closeColor = when {
        closingPrice > monthlyAvgPrice -> StockUpColor
        closingPrice < monthlyAvgPrice -> StockDownColor
        else -> StockTextPrimary
    }
    val openColor = when {
        openingPrice > monthlyAvgPrice -> StockUpColor
        openingPrice < monthlyAvgPrice -> StockDownColor
        else -> StockTextPrimary
    }
    val changeColor = when {
        change > 0 -> StockUpColor
        change < 0 -> StockDownColor
        else -> StockTextPrimary
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {

            // 代號
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.code,
                    color = StockTextSecondary,
                    fontSize = 13.sp
                )

                if (info) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = InfoIconColor,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(start = 4.dp)
                    )
                }
            }

            // 名稱
            Text(
                text = item.name,
                color = StockTextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 開盤價 / 收盤價
            MultiColumnRows(
                fontSize = 13.sp,
                rows = listOf(
                    listOf(
                        Cell(
                            label = stringResource(R.string.opening_price),
                            value = item.openingPrice,
                            valueColor = openColor,
                        ),
                        Cell(
                            label = stringResource(R.string.closing_price),
                            value = item.closingPrice,
                            valueColor = closeColor
                        )
                    )
                ),
            )

            // 最高價 / 最低價
            MultiColumnRows(
                fontSize = 13.sp,
                rows = listOf(
                    listOf (
                        Cell(
                            label = stringResource(R.string.highest_price),
                            value = item.highestPrice
                        ),
                        Cell(stringResource(R.string.lowest_price),
                            item.lowestPrice
                        ),
                    )
                ),
            )

            // 漲跌差 / 月平均價
            MultiColumnRows(
                fontSize = 13.sp,
                rows = listOf(
                    listOf (
                        Cell(
                            label = stringResource(R.string.change),
                            value = item.change,
                            valueColor = changeColor,
                        ),
                        Cell(
                            label = stringResource(R.string.monthly_average_price),
                            value = "%.2f".format(monthlyAvgPrice)
                        )
                    )
                ),
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 成交金額 / 成交股數 / 成交筆數
            MultiColumnRows(
                fontSize = 10.sp,
                rows = listOf(
                    listOf (
                        Cell(
                            label = stringResource(R.string.trade_value),
                            value = item.transaction
                        ),
                        Cell(
                            label = stringResource(R.string.trade_volume),
                            value = item.tradeVolume
                        ),
                        Cell(
                            label = stringResource(R.string.transaction),
                            value = item.tradeValue
                        )
                    )
                ),
                isDouble = false
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = Color.WHITE.toLong()
)
@Composable
fun StockItemPreview() {
    val viewModel : StockAllViewModel = viewModel()
    StockItem(
        viewModel = viewModel,
        item = viewModel.previewStockAll,
        monthlyAvg = "50.0",
        info = false
    ) {

    }
}
