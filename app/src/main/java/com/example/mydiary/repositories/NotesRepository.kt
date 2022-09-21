package com.example.mydiary.repositories

import androidx.paging.PagingSource
import com.example.mydiary.db.entity.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun saveNote(note: Note)

    fun getAllNotes(): Flow<List<Note>>

    fun getNotesPagingSource(): PagingSource<Int, Note>

}