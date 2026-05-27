package com.mittal.mydiary.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mittal.mydiary.db.entity.Note
import com.mittal.mydiary.repositories.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

private const val VM_TAG = "AddNoteViewModel"

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddNoteUiState())
    val uiState: StateFlow<AddNoteUiState> = _uiState.asStateFlow()

    fun onIntent(intent: AddNoteIntent) {
        when (intent) {
            is AddNoteIntent.TitleChanged -> {
                _uiState.update { it.copy(title = intent.newTitle) }
            }
            is AddNoteIntent.BodyChanged -> {
                _uiState.update { it.copy(body = intent.newBody) }
            }
            AddNoteIntent.SaveNote -> {
                saveNote()
            }
        }
    }

    private fun saveNote() {
        val currentState = _uiState.value
        Log.i(VM_TAG, "saveNote() title:${currentState.title}, body:${currentState.body}")
        
        if (currentState.title.isBlank() && currentState.body.isBlank()) return

        _uiState.update { it.copy(isSaving = true) }

        viewModelScope.launch(Dispatchers.IO) {
            val currentDateTime = Date()
            notesRepository.saveNote(
                Note(
                    noteId = 0,
                    title = currentState.title,
                    body = currentState.body,
                    createdDate = currentDateTime
                )
            )
            // Note: In a full MVI, we might emit an "Effect" to finish the activity,
            // but for now, we'll keep the Activity's logic of calling onBack().
            _uiState.update { it.copy(isSaving = false) }
        }
    }
}
