package com.example.mydiary.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydiary.db.entity.Note
import com.example.mydiary.repositories.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : ViewModel() {

    val titleTF = mutableStateOf("")
    val bodyTF = mutableStateOf("")

    fun saveNote() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentDateTime = Date()
            var a = notesRepository.saveNote(Note(0, titleTF.value, bodyTF.value, currentDateTime))
        }
    }
}