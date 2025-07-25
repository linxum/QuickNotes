package com.smekhnyov.quicknotes.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smekhnyov.quicknotes.ui.theme.QuickNotesTheme


@Composable
fun NoteListItem(title: String, body: String = "This is a note body", onClick: () -> Unit = {}) {
    QuickNotesTheme {
        Surface(
            onClick = { onClick() },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            shape = androidx.compose.material3.MaterialTheme.shapes.large,
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 8.dp
                )
            ) {
                androidx.compose.material3.Text(
                    text = title,
                    style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Start,
                    maxLines = 1,
                )
                androidx.compose.material3.Text(
                    text = body,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Start,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteListItemPreview() {
    NoteListItem(
        title = "Sample Note Title",
        body = "This is a sample note body for preview purposes."
    )
}