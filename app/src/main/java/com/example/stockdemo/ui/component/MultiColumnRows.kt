package com.example.stockdemo.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stockdemo.data.Cell
import com.example.stockdemo.viewmodel.StockAllViewModel
import java.util.Locale
import kotlin.collections.listOf

@Composable
fun MultiColumnRows(
    rows: List<List<Cell>>,
    fontSize: TextUnit,
    isDouble: Boolean = true
) {
    Column {
        rows.forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                row.forEach { cell ->

                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "${cell.label} ",
                            color = cell.labelColor,
                            fontSize = fontSize
                        )

                        Text(
                            text = format(cell.value, isDouble),
                            color = cell.valueColor,
                            fontSize = fontSize,
                            fontFamily = FontFamily.Monospace,
                            maxLines = 1,
                            softWrap = false,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

fun format(value: String, isDouble: Boolean = true): String {
    val clean = value.replace(",", "")
    val number = clean.toDoubleOrNull()

    return number?.let {
        if (isDouble) {
            String.format(Locale.US, "%,.2f", it)
        } else {
            String.format(Locale.US, "%, d", it.toLong())
        }
    } ?: value
}

@Preview(
    showBackground = true,
)
@Composable
fun MultiColumnRowsRowsPreview() {
    MultiColumnRows(
        fontSize = 13.sp,
        rows = listOf(
            listOf(
                Cell(
                    label = "開盤價",
                    value = "580.00"
                ),
                Cell(
                    label = "收盤價",
                    value = "585.00"
                )
            ),

            listOf(
                Cell(
                    label = "最高價",
                    value = "582.00"
                ),
                Cell(
                    label = "最低價",
                    value = "573.00"
                )
            ),

            listOf(
                Cell(
                    label = "漲跌價差",
                    value = "5.00"
                ),
                Cell(
                    label = "月平均價",
                    value = "565.00"
                )
            ),

            listOf(
                Cell(
                    label = "成交筆數",
                    value = "12,345"
                ),
                Cell(
                    label = "成交股數",
                    value = "12,345"
                ),
                Cell(
                    label = "成交金額",
                    value = "12,345"
                )
            )
        ),
    )
}
