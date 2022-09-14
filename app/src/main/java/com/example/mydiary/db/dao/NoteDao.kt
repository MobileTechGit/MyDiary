package com.example.mydiary.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mydiary.db.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    fun getAll(): Flow<List<Note>>

    @Query("SELECT * FROM Note WHERE noteId IN (:noteIds)")
    fun loadAllByIds(noteIds: IntArray): List<Note>

    @Query("SELECT * FROM Note WHERE title LIKE :title AND " +
            "body LIKE :body LIMIT 1")
    fun findByName(title: String, body: String): Flow<Note>

    @Insert
    fun insertAll(vararg notes: Note)

    @Delete
    fun delete(user: Note)
}