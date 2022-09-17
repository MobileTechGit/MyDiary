package com.example.mydiary.repositories

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.example.mydiary.db.entity.Note
import com.example.mydiary.db.room.DiaryDatabase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepository(context: Context) {

    private val diaryDatabase: DiaryDatabase = getDiaryDatabase(context)

    fun saveNote(note: Note) {
        diaryDatabase.noteDao().insertAll(note)
    }

    fun getAllNotes(): Flow<List<Note>> {
        return diaryDatabase.noteDao().getAll()
    }

    fun getNotesPagingSource(): PagingSource<Int, Note> {
        return diaryDatabase.noteDao().getPagingSource()
    }

    @InstallIn(SingletonComponent::class)
    @EntryPoint
    interface NotesRepositoryEntryPoint {
        fun getDiaryDatabase(): DiaryDatabase
    }

    private fun getDiaryDatabase(appContext: Context): DiaryDatabase {
        val hiltEntryPoint = EntryPointAccessors.fromApplication(
            appContext,
            NotesRepositoryEntryPoint::class.java
        )
        return hiltEntryPoint.getDiaryDatabase()
    }
}