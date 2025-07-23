package com.smekhnyov.quicknotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.smekhnyov.quicknotes.ui.QuickNotesNavGraph
import com.smekhnyov.quicknotes.ui.theme.QuickNotesTheme
import com.smekhnyov.quicknotes.data.NoteDatabase
import com.smekhnyov.quicknotes.domain.NoteRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val database = NoteDatabase.getInstance(applicationContext)
        val repository = NoteRepository(database.noteDao())
        setContent {
            QuickNotesTheme {
                QuickNotesNavGraph(repository = repository)
            }
        }
    }
}