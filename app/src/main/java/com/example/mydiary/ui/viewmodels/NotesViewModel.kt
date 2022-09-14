package com.example.mydiary.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydiary.db.entity.Note
import com.example.mydiary.repositories.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

const val TAG = "NotesViewModel"
@HiltViewModel
class NotesViewModel @Inject constructor() : ViewModel() {
    @Inject @Named("NotesRepo") lateinit var notesRepository: NotesRepository

    fun saveNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.saveNote(note)
        }
    }

    fun getAllNotes(): Flow<List<Note>> {
        return notesRepository.getAllNotes()
    }
}