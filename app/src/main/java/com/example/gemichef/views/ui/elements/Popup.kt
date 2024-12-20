package com.example.gemichef.views.ui.elements

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.gemichef.R

@Composable
fun Popup(
    showPopup: Boolean,
    title: String,
    message: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {

    if (showPopup) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            title = {
                Text(text = title)
            },
            text = {
                Text(text = message)
            },
            confirmButton = {
                Button(onClick = onConfirm) {
                    Text(stringResource(R.string.ok))
                }
            }
        )
    }
}

@Preview
@Composable
fun PopupPreview() {
    Popup(
        showPopup = true,
        title = "Title",
        message = "Message",
        onDismiss = {},
        onConfirm = {}
    )
}