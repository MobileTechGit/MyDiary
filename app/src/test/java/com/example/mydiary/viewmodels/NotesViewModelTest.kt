package com.example.mydiary.viewmodels

import com.example.mydiary.repositories.FakeNotesRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class NotesViewModelTest {

    private lateinit var notesViewModel: NotesViewModel

    @Before
    fun setUp() {
        notesViewModel = NotesViewModel(FakeNotesRepository())
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `save note with valid input, return success`() {
        notesViewModel.saveNote("title1", "body1")
        runTest {
            println(notesViewModel.getAllNotes().first())
            notesViewModel.getAllNotes().first()[0].apply {
                assertThat(title == "title1" && body == "body1").isTrue()
            }
        }
    }
}