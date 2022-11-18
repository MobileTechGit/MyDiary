package com.mittal.mydiary.repositories

import androidx.paging.PagingSource
import com.mittal.mydiary.db.entity.Note
import com.mittal.mydiary.db.room.DiaryDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val diaryDatabase: DiaryDatabase
) : NotesRepository {

    override suspend fun saveNote(note: Note) {
        diaryDatabase.noteDao().insertAll(note)
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return diaryDatabase.noteDao().getAll()
    }

    override fun getNotesPagingSource(): PagingSource<Int, Note> {
        return diaryDatabase.noteDao().getPagingSource()
    }

    /*@InstallIn(SingletonComponent::class)
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
    }*/
}