package com.example.mydiary.di.module

import android.content.Context
import android.util.Log
import com.example.mydiary.repositories.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @ViewModelScoped
    @Provides
    @Named("NotesRepo")
    fun getNotesRepository(@ApplicationContext context: Context) : NotesRepository {
        Log.i("object RepositoryModule", "getNotesRepository: ")
        return NotesRepository(context)
    }
}