package com.smekhnyov.quicknotes.ui.noteEdit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.smekhnyov.quicknotes.ui.theme.QuickNotesTheme
import com.smekhnyov.quicknotes.R
import com.smekhnyov.quicknotes.data.Note
import com.smekhnyov.quicknotes.data.NoteDatabase
import com.smekhnyov.quicknotes.domain.NoteRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEdit(
    note: Note? = null,
    navController: androidx.navigation.NavHostController,
    repository: NoteRepository,
    vm: NoteEditViewModel = viewModel(
        factory = NoteEditViewModelFactory(repository)
    )
) {
    var title by remember { mutableStateOf(note?.title ?: "") }
    var body by remember { mutableStateOf(note?.body ?: "") }
    QuickNotesTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = if (note == null) stringResource(R.string.new_note) else stringResource(
                                R.string.edit_note
                            )
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back)
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        vm.saveNote(
                            Note(id = note?.id ?: 0, title = title, body = body)
                        )
                        navController.navigateUp()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = stringResource(R.string.save_note)
                    )
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(text = stringResource(R.string.note_title)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    )
                )
                TextField(
                    value = body,
                    onValueChange = { body = it },
                    label = { Text(text = stringResource(R.string.note_body)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    maxLines = Int.MAX_VALUE,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun NoteEditPreview() {
    NoteEdit(
        note = Note(
            id = 1,
            title = "Sample Note Title",
            body = "This is a sample note body for preview purposes.",
            updatedAt = 1696158000000L
        ),
        navController = NavHostController(context = androidx.compose.ui.platform.LocalContext.current),
        repository = NoteRepository(
            NoteDatabase.getInstance(androidx.compose.ui.platform.LocalContext.current).noteDao()
        )
    )
}

@Preview(showSystemUi = false)
@Composable
fun NoteNewPreview() {
    NoteEdit(
        note = null,
        navController = NavHostController(context = androidx.compose.ui.platform.LocalContext.current),
        repository = NoteRepository(
            NoteDatabase.getInstance(androidx.compose.ui.platform.LocalContext.current).noteDao()
        )
    )
}