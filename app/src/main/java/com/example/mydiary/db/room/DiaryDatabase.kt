package com.example.mydiary.db.room

import android.content.Context
import androidx.room.*
import com.example.mydiary.db.dao.NoteDao
import com.example.mydiary.db.entity.Note


/*,
autoMigrations = [
AutoMigration (from = 1, to = 2)
]*/

@Database(entities = [Note::class], version = 1)
@TypeConverters(Converters::class)
abstract class DiaryDatabase : RoomDatabase(){
    abstract fun noteDao() : NoteDao

    companion object {
        @Volatile
        private var INSTANCE: DiaryDatabase? = null

        fun getDatabase(context: Context) : DiaryDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    DiaryDatabase::class.java, "my_diary_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}