package com.example.mydiary.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mydiary.R
import com.example.mydiary.viewmodels.AddNoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteActivity : AppCompatActivity() {

    private val addNoteVM by viewModels<AddNoteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddNoteForm(addNoteVM)
        }
    }

    @Composable
    fun AddNoteForm(addNoteVM1: AddNoteViewModel) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = addNoteVM1.titleTF.value,
                label = { Text(text = getString(R.string.title_label)) },
                onValueChange = { addNoteVM1.titleTF.value = it },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(.9f)
            )

            Spacer(modifier = Modifier.height(5.dp))

            TextField(
                value = addNoteVM1.bodyTF.value,
                label = { Text(text = getString(R.string.add_note_label)) },
                onValueChange = { addNoteVM1.bodyTF.value = it },
                modifier = Modifier.fillMaxWidth(.9f)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Button(onClick = {
                addNoteVM1.saveNote()
                finish()
            }) {
                Text(text = getString(R.string.save_label))
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewFun() {
        AddNoteForm(addNoteVM)
    }
}