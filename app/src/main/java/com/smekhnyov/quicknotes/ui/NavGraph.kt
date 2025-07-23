package com.smekhnyov.quicknotes.ui

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smekhnyov.quicknotes.ui.noteList.NoteList
import com.smekhnyov.quicknotes.ui.noteView.NoteView
import com.smekhnyov.quicknotes.data.Note
import com.smekhnyov.quicknotes.domain.NoteRepository
import com.smekhnyov.quicknotes.ui.noteEdit.NoteEdit

sealed class Screen(val route: String) {
    object NoteList : Screen("note_list")
    object NoteView : Screen("note_view/{noteId}") {
        fun createRoute(noteId: Int) = "note_view/$noteId"
    }

    object NoteEdit : Screen("note_edit/{noteId}") {
        fun createRoute(noteId: Int) = "note_edit/$noteId"
    }
}

@Composable
fun QuickNotesNavGraph(
    repository: NoteRepository,
    navController: NavHostController = rememberNavController()
) {
    var notes by remember { mutableStateOf<List<Note>>(emptyList()) }

    LaunchedEffect(Unit) {
        notes = repository.getAll()
    }

    NavHost(
        navController = navController,
        startDestination = Screen.NoteList.route
    ) {
        composable(Screen.NoteList.route) {
            // Refresh notes when navigating to NoteList
            // getAll is suspend
            // so we use LaunchedEffect to call it
            LaunchedEffect(Unit) {
                notes = repository.getAll()
            }
            NoteList(
                notes = notes,
                onNoteClick = { noteId: Int ->
                    navController.navigate(Screen.NoteView.createRoute(noteId))
                },
                onAddNote = {
                    navController.navigate(Screen.NoteEdit.createRoute(-1))
                }
            )
        }
        composable(Screen.NoteView.route) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toIntOrNull()
            val note = notes.find { it.id == noteId }
            note?.let {
                NoteView(note = it, navController = navController, repository = repository)
            }
        }
        composable(Screen.NoteEdit.route) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toIntOrNull()
            val note = notes.find { it.id == noteId }
            NoteEdit(
                note = note,
                navController = navController,
                repository = repository,
            )
        }
    }
}