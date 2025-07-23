package com.smekhnyov.quicknotes.domain

import com.smekhnyov.quicknotes.data.Note
import com.smekhnyov.quicknotes.data.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun getAll(): List<Note> {
        return noteDao.getAll()
    }

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun update(note: Note) {
        note.updatedAt = System.currentTimeMillis()
        noteDao.update(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    suspend fun getById(id: Int): Note? {
        return noteDao.getById(id)
    }
}