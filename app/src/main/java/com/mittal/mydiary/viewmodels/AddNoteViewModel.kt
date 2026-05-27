package com.mittal.mydiary.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mittal.mydiary.db.entity.Note
import com.mittal.mydiary.repositories.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : ViewModel() {

    private val _title = mutableStateOf("")
    val title: State<String> = _title

    private val _body = mutableStateOf("")
    val body: State<String> = _body

    fun onTitleChange(newValue: String) {
        _title.value = newValue
    }

    fun onBodyChange(newValue: String) {
        _body.value = newValue
    }

    fun saveNote() {
        Log.i(TAG, "saveNote() title:${_title.value}, body:${_body.value}")
        if (_title.value.isBlank() && _body.value.isBlank()) return

        viewModelScope.launch(Dispatchers.IO) {
            val currentDateTime = Date()
            notesRepository.saveNote(Note(0, _title.value, _body.value, currentDateTime))
        }
    }
}