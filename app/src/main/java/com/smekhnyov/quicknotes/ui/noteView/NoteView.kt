package com.smekhnyov.quicknotes.ui.noteView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.smekhnyov.quicknotes.R
import com.smekhnyov.quicknotes.data.Note
import com.smekhnyov.quicknotes.data.NoteDatabase
import com.smekhnyov.quicknotes.domain.NoteRepository
import com.smekhnyov.quicknotes.ui.components.DeleteDialog
import com.smekhnyov.quicknotes.ui.theme.QuickNotesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteView(
    _note: Note,
    navController: NavHostController,
    repository: NoteRepository,
    updateState: MutableState<Boolean>,
    vm: NoteViewModel = viewModel(
        factory = NoteViewModelFactory(repository)
    )
) {
    val note: Note
    if (!updateState.value) {
        note = _note
    } else {
        note = vm.getNote(_note.id)!!
    }


    var showDeleteDialog by remember { mutableStateOf(false) }
    QuickNotesTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = note.title) },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back)
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            navController.navigate("note_edit/${note.id}")
                        }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = stringResource(R.string.edit_note)
                            )
                        }
                        IconButton(onClick = { showDeleteDialog = true }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = stringResource(R.string.delete_note)
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column {
                Text(
                    text = note.body,
                    modifier = androidx.compose.ui.Modifier
                        .padding(innerPadding)
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                    style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = stringResource(R.string.last_update) + java.text.SimpleDateFormat("d MMMM yyyy HH:mm")
                        .format(java.util.Date(note.updatedAt)),
                    modifier = androidx.compose.ui.Modifier
                        .padding(innerPadding)
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    style = androidx.compose.material3.MaterialTheme.typography.labelSmall
                )
            }

        }
    }
    if (showDeleteDialog) {
        DeleteDialog(
            onDeleteCancelled = { showDeleteDialog = false },
            onDeleteConfirmed = {
                vm.deleteNote(note)
                showDeleteDialog = false
                navController.navigateUp()
            }
        )
    }
}

@Preview
@Composable
fun NoteViewPreview() {
    NoteView(
        navController = NavHostController(context = androidx.compose.ui.platform.LocalContext.current),
        _note = Note(
            id = 1,
            title = "Sample Note",
            body = "This is a sample note content.",
            updatedAt = System.currentTimeMillis(),
        ),
        repository = NoteRepository(
            NoteDatabase.getInstance(context = androidx.compose.ui.platform.LocalContext.current)
                .noteDao()
        ),
        updateState = remember { mutableStateOf<Boolean>(false) },
    )
}