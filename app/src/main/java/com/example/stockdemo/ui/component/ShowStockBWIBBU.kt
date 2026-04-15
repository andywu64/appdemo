package com.example.stockdemo.ui.component

import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stockdemo.R
import com.example.stockdemo.ui.util.ShowMessage
import com.example.stockdemo.util.textValueDefault
import com.example.stockdemo.viewmodel.StockAllViewModel

@Composable
fun ShowStockBWIBBU(
    viewModel: StockAllViewModel,
    onConfirmation: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    val stockBWIBBUItems by viewModel.stockBWIBBUAllState.collectAsState()
    val code by viewModel.stockCodeState.collectAsState()
    val item = stockBWIBBUItems.find {
        it.code == code
    }

    if (item != null) {
        val textColor = MaterialTheme.colorScheme.surface
        val textSizeTitle = 16.sp
        val textSizeName = 24.sp
        val textSizeCode = 14.sp
        Dialog(
            onDismissRequest = onDismissRequest
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .width(250.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp, horizontal = 20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(5.dp)
                    ) {
                        Text(
                            text = item.name,
                            fontSize = textSizeName,
                            color = textColor,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = item.code,
                            fontSize = textSizeCode,
                            color = textColor,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.Top)
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .padding(5.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.pe_ratio),
                            fontSize = textSizeTitle,
                            color = textColor,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .weight(2f)
                        )
                        Text(
                            text = textValueDefault(item.pERatio),
                            fontSize = textSizeTitle,
                            color = textColor,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .padding(5.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.dividend_yield),
                            fontSize = textSizeTitle,
                            color = textColor,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .weight(2f)
                        )
                        Text(
                            text = textValueDefault(item.dividendYield),
                            fontSize = textSizeTitle,
                            color = textColor,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .padding(5.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.pb_ratio),
                            fontSize = textSizeTitle,
                            color = textColor,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .weight(2f)
                        )
                        Text(
                            text = textValueDefault(item.pBRatio),
                            fontSize = textSizeTitle,
                            color = textColor,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.secondary,
                            containerColor = MaterialTheme.colorScheme.secondary,
                            disabledContainerColor = MaterialTheme.colorScheme.secondary,
                            disabledContentColor = MaterialTheme.colorScheme.secondary
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = onConfirmation
                    ) {
                        Text(
                            stringResource(R.string.confirm),
                            fontSize = textSizeTitle,
                            color = textColor,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    } else {
        ShowMessage(
            title = stringResource(R.string.no_info_now),
            message = "",
            onConfirmation = onConfirmation,
            onDismissRequest = onDismissRequest
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = Color.LTGRAY.toLong()
)
@Composable
fun ShowStockBWIBBUPreview() {
    val viewMode : StockAllViewModel = viewModel()
    viewMode.setPreviewStockBWIBBUCode()
    viewMode.setPreviewStockBWIBBU()
    ShowStockBWIBBU(
        viewModel = viewMode,
        onConfirmation = {

        },
    ) {

    }
}
