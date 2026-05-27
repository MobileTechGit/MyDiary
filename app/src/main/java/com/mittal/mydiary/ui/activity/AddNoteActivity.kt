package com.mittal.mydiary.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mittal.mydiary.R
import com.mittal.mydiary.viewmodels.AddNoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteActivity : AppCompatActivity() {

    private val addNoteVM by viewModels<AddNoteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddNoteScreen(
                viewModel = addNoteVM
            ) { finish() }
        }
    }
}

@Composable
fun AddNoteScreen(
    viewModel: AddNoteViewModel,
    onBack: () -> Unit
) {
    AddNoteForm(
        title = viewModel.title.value,
        onTitleChange = viewModel::onTitleChange,
        body = viewModel.body.value,
        onBodyChange = viewModel::onBodyChange,
        onSave = {
            viewModel.saveNote()
            onBack()
        }
    )
}

@Composable
fun AddNoteForm(
    title: String,
    onTitleChange: (String) -> Unit,
    body: String,
    onBodyChange: (String) -> Unit,
    onSave: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = title,
            label = { Text(text = stringResource(R.string.title_label)) },
            onValueChange = onTitleChange,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = body,
            label = { Text(text = stringResource(R.string.add_note_label)) },
            onValueChange = onBodyChange,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onSave) {
            Text(text = stringResource(R.string.save_label))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddNoteForm() {
    AddNoteForm(
        title = "Sample Title",
        onTitleChange = {},
        body = "Sample Body Text",
        onBodyChange = {},
        onSave = {}
    )
}