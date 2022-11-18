package com.mittal.mydiary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.mittal.mydiary.db.entity.Note
import com.mittal.mydiary.repositories.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

const val TAG = "NotesViewModel"

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesRepository: NotesRepository) : ViewModel() {
//    @Inject
//    @Named("NotesRepo")
//    private lateinit var notesRepository: NotesRepository

    val pagingNotesFlow: Flow<PagingData<Note>>

    init {
        val pagingConfig = PagingConfig(pageSize = 30, enablePlaceholders = true, maxSize = 100)
        pagingNotesFlow = Pager(config = pagingConfig) {
            getNotesPagingSource()
        }.flow.cachedIn(viewModelScope)
    }

    fun saveNote(title: String, body: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val currentDateTime = Date()
            var a = notesRepository.saveNote(Note(0, title, body, currentDateTime))
        }
    }

    fun getAllNotes(): Flow<List<Note>> {
        return notesRepository.getAllNotes()
    }

    private fun getNotesPagingSource(): PagingSource<Int, Note> {
        return notesRepository.getNotesPagingSource()
    }
}