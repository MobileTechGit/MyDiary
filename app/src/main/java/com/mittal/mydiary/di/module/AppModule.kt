package com.mittal.mydiary.di.module

import android.content.Context
import com.mittal.mydiary.db.room.DiaryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDiaryDatabase(@ApplicationContext context: Context) : DiaryDatabase {
        return DiaryDatabase.getDatabase(context)
    }

}