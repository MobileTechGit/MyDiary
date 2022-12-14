package com.mittal.mydiary.db.room

import android.content.Context
import androidx.room.*
import com.mittal.mydiary.constants.RoomConstants
import com.mittal.mydiary.db.dao.NoteDao
import com.mittal.mydiary.db.entity.Note


/*,
autoMigrations = [
AutoMigration (from = 1, to = 2)
]*/

@Database(entities = [Note::class], version = 1)
@TypeConverters(Converters::class)
abstract class DiaryDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: DiaryDatabase? = null

        fun getDatabase(applicationContext: Context): DiaryDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    applicationContext,
                    DiaryDatabase::class.java,
                    RoomConstants.DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}