package com.mlefrapper.androidstarterkit.ui.components.dialog

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AlertDialogASK(
    dialogTitle: String,
    dialogMessage: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    confirmButtonText: String,
    dismissButtonText: String,
    imageVector: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
            )
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogMessage)
        },
        onDismissRequest = {
            onDismiss.invoke()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm.invoke()
                },
            ) {
                Text(text = confirmButtonText)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss.invoke()
                },
            ) {
                Text(text = dismissButtonText)
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun AlertDialogASKPreview() {
    AlertDialogASK(
        dialogTitle = "Dialog Title",
        dialogMessage = "This is a dialog message.",
        onDismiss = { },
        onConfirm = { },
        confirmButtonText = "Confirm",
        dismissButtonText = "Dismiss",
        imageVector = Icons.Filled.Info,
    )
}
