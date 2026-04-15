package com.example.stockdemo.ui.util

import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockdemo.R

@Composable
fun ShowMessage(
    title: String,
    message: String,
    onConfirmation: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    val textColor = MaterialTheme.colorScheme.surface
    val textSizeTitle = 24.sp
    val textSizeValue = 16.sp
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.tertiary,
        onDismissRequest = onDismissRequest,
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Text(
                    text = title,
                    fontSize = textSizeTitle,
                    color = textColor,
                    textAlign = TextAlign.Center,
                )
            }
        },
        text = {
            if (message.isNotEmpty()) {
                Text(
                    text = message,
                    fontSize = textSizeValue,
                    color = textColor,
                    textAlign = TextAlign.Center,
                )
            }
        },
        confirmButton = {
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
                    fontSize = textSizeValue,
                    color = textColor,
                    textAlign = TextAlign.Center,
                )
            }
        }
    )
}

@Preview(
    showBackground = true,
    backgroundColor = Color.LTGRAY.toLong()
)
@Composable
fun ShowMessagePreview() {
    ShowMessage (
        title = "測試",
        message = "",
        onConfirmation = {

        }
    ) {

    }
}
