package com.smekhnyov.quicknotes.ui.noteList

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import com.smekhnyov.quicknotes.R
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.smekhnyov.quicknotes.ui.theme.QuickNotesTheme
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import com.smekhnyov.quicknotes.data.Note
import com.smekhnyov.quicknotes.ui.components.NoteListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteList(
    notes: List<Note>,
    onNoteClick: (Int) -> Unit,
    onAddNote: () -> Unit
) {
    QuickNotesTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) },
                    colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
                        titleContentColor = androidx.compose.material3.MaterialTheme.colorScheme.primary
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { onAddNote() }) {
                    // Action for the FloatingActionButton can be defined here
                    // For now, we just display a placeholder text
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = stringResource(id = R.string.new_note)
                    )
                }
            }
        ) { innerPadding ->
            LazyColumn(modifier = androidx.compose.ui.Modifier.padding(innerPadding)) {
                items(notes.size) { index ->
                    NoteListItem(
                        title = notes[index].title,
                        body = notes[index].body,
                        onClick = { onNoteClick(notes[index].id) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NoteListPreview() {
    val notes = listOf(
        Note(id = 1, title = "Sample Note 1", body = "This is the body of sample note 1"),
        Note(id = 2, title = "Sample Note 2", body = "This is the body of sample note 2")
    )
    NoteList(
        notes,
        onNoteClick = { /* Handle note click */ },
        onAddNote = { /* Handle add note */ })
}