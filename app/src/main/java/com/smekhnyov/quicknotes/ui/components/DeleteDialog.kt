package com.smekhnyov.quicknotes.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.smekhnyov.quicknotes.R

@Composable
fun DeleteDialog(
    onDeleteConfirmed: () -> Unit,
    onDeleteCancelled: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDeleteCancelled() },
        title = { Text(text = stringResource(R.string.delete_confirm)) },
        confirmButton = {
            TextButton(
                onClick = { onDeleteConfirmed() },
            ) {
                Text(text = stringResource(R.string.yes))
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDeleteCancelled() },
            ) {
                Text(text = stringResource(R.string.no))
            }
        }
    )
}