package com.example.mydiary.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.mydiary.db.entity.Note
import com.example.mydiary.repositories.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import javax.inject.Named

const val TAG = "NotesViewModel"

@HiltViewModel
class NotesViewModel @Inject constructor() : ViewModel() {
    @Inject
    @Named("NotesRepo")
    lateinit var notesRepository: NotesRepository

    val pagingNotesFlow: Flow<PagingData<Note>>

    init {
        val pagingConfig = PagingConfig(pageSize = 30, enablePlaceholders = true, maxSize = 100)
        pagingNotesFlow = Pager(config = pagingConfig) {
            getNotesPagingSource()
        }.flow.cachedIn(viewModelScope)
    }

    fun saveNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.saveNote(note)
        }
    }

    fun saveNote(title: String, body: String) {
        val currentDateTime = Date()
        saveNote(Note(0, title, body, currentDateTime))
    }

    fun getAllNotes(): Flow<List<Note>> {
        return notesRepository.getAllNotes()
    }

    private fun getNotesPagingSource(): PagingSource<Int, Note> {
        return notesRepository.getNotesPagingSource()
    }
}