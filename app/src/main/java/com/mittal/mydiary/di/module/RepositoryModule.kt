package com.mittal.mydiary.di.module

import android.util.Log
import com.mittal.mydiary.db.room.DiaryDatabase
import com.mittal.mydiary.repositories.NotesRepository
import com.mittal.mydiary.repositories.NotesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @ViewModelScoped
    @Provides
    fun getNotesRepository(diaryDatabase: DiaryDatabase) : NotesRepository {
        Log.i("object RepositoryModule", "getNotesRepository: ")
        return NotesRepositoryImpl(diaryDatabase)
    }

    @ViewModelScoped
    @Provides
    @Named("NotesRepo")
    fun getNotesRepo(diaryDatabase: DiaryDatabase) : NotesRepository {
        Log.i("object RepositoryModule", "getNotesRepository: ")
        return NotesRepositoryImpl(diaryDatabase)
    }
}