package com.example.mydiary.application

import android.app.Application
import androidx.room.Room
import com.example.mydiary.db.room.DiaryDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppApplication : Application() {



}