package com.example.mydiary.repositories

import androidx.paging.PagingSource
import com.example.mydiary.db.entity.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeNotesRepository : NotesRepository {

    private val notesList = mutableListOf<Note>()
    private val observableNotesList: Flow<List<Note>> = flowOf(notesList)

    override suspend fun saveNote(note: Note) {
        notesList.add(note)
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return observableNotesList
    }

    override fun getNotesPagingSource(): PagingSource<Int, Note> {
        TODO("Not yet implemented")
    }
}