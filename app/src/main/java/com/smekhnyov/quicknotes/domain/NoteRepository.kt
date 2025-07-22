package com.smekhnyov.quicknotes.domain

import com.smekhnyov.quicknotes.data.Note
import com.smekhnyov.quicknotes.data.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun getAllNotes(): List<Note> {
        return noteDao.getAllNotes()
    }

    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    suspend fun updateNote(note: Note) {
        note.updatedAt = System.currentTimeMillis()
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}