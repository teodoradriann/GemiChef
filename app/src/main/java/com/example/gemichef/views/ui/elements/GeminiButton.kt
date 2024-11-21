package com.example.gemichef.views.ui.elements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GeminiButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
        Button(
            onClick = onClick,
            shape = MaterialTheme.shapes.large,
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = modifier.padding(4.dp)
                )
            }
        }
}

@Preview
@Composable
fun GeminiButtonPreview() {

}