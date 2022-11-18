package com.mittal.mydiary.db.dao

import androidx.test.filters.SmallTest
import com.mittal.mydiary.db.entity.Note
import com.mittal.mydiary.db.room.DiaryDatabase
import com.mittal.mydiary.launchFragmentInHiltContainer
import com.mittal.mydiary.ui.fragment.LoginFragment
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
class NoteDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject @Named("test_db") lateinit var database: DiaryDatabase
    lateinit var noteDao: NoteDao

    @Before
    fun setup() {
        hiltRule.inject()
        noteDao = database.noteDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertNote() = runTest {
        val note = Note(1, "Title", "Body", Date())
        noteDao.insertAll(note)
        println(note)
        val notesList = noteDao.getAll().first()
        assertThat(notesList).contains(note)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteNote() = runTest {
        val note = Note(1, "Title", "Body", Date())
        noteDao.insertAll(note)
        noteDao.delete(note)

        val notesList = noteDao.getAll().first()
        assertThat(notesList).doesNotContain(note)
    }

    @Test
    fun testLaunchFragmentInHiltContainer() {
        launchFragmentInHiltContainer<LoginFragment> {

        }
    }

}