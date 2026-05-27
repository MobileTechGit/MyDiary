package com.mittal.mydiary.viewmodels

/**
 * State representing the UI for the AddNote screen.
 */
data class AddNoteUiState(
    val title: String = "",
    val body: String = "",
    val isSaving: Boolean = false
)

/**
 * User intentions/actions for the AddNote screen.
 */
sealed class AddNoteIntent {
    data class TitleChanged(val newTitle: String) : AddNoteIntent()
    data class BodyChanged(val newBody: String) : AddNoteIntent()
    object SaveNote : AddNoteIntent()
}
