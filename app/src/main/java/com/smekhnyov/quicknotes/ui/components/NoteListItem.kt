package com.smekhnyov.quicknotes.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smekhnyov.quicknotes.ui.theme.QuickNotesTheme


@Composable
fun NoteListItem(title: String, body: String = "This is a note body") {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)) {
        QuickNotesTheme {
            androidx.compose.material3.Text(text = title, style = androidx.compose.material3.MaterialTheme.typography.titleLarge)
            androidx.compose.material3.Text(text = body, style = androidx.compose.material3.MaterialTheme.typography.bodyMedium)
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