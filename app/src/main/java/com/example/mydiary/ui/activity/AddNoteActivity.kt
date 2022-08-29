package com.example.mydiary.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mydiary.R
import com.example.mydiary.databinding.ActivityAddNoteBinding
import com.example.mydiary.db.entity.Note
import com.example.mydiary.ui.viewmodels.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private val notesViewModel by viewModels<NotesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note)

        binding.addNoteActivity = this
        binding.name = "Add Note Activity"
    }

    fun onSaveClick() {
        binding.apply {
            notesViewModel.saveNote(Note(0, titleEt.text.toString(), bodyEt.text.toString()))
        }
    }
}