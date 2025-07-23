package com.smekhnyov.quicknotes.ui.noteEdit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.smekhnyov.quicknotes.data.Note
import com.smekhnyov.quicknotes.domain.NoteRepository
import kotlinx.coroutines.launch

class NoteEditViewModel(private val repository: NoteRepository) : ViewModel() {
    private val _notes = MutableLiveData<List<Note>>()

    init {
        viewModelScope.launch {
            _notes.value = repository.getAll()
        }
    }

    fun saveNote(note: Note) {
        viewModelScope.launch {
            if (note.id == 0) {
                repository.insert(note)
                _notes.value = repository.getAll()
            } else {
                repository.update(note)
                _notes.value = repository.getAll()
            }
        }
    }
}

class NoteEditViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteEditViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteEditViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}