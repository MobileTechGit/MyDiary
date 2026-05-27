package com.mittal.mydiary.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mittal.mydiary.R
import com.mittal.mydiary.ui.activity.ui.theme.MyDiaryTheme
import com.mittal.mydiary.viewmodels.AddNoteIntent
import com.mittal.mydiary.viewmodels.AddNoteUiState
import com.mittal.mydiary.viewmodels.AddNoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteActivity : AppCompatActivity() {

    private val addNoteVM by viewModels<AddNoteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDiaryTheme {
                val state by addNoteVM.uiState.collectAsState()

                AddNoteScreen(
                    state = state,
                    onIntent = { intent ->
                        addNoteVM.onIntent(intent)
                        if (intent is AddNoteIntent.SaveNote) {
                            finish()
                        }
                    },
                    onBack = { finish() }
                )
            }
        }
    }
}

@Composable
fun AddNoteScreen(
    state: AddNoteUiState,
    onIntent: (AddNoteIntent) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.add_note_label)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                elevation = 4.dp
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AddNoteForm(
                title = state.title,
                onTitleChange = { onIntent(AddNoteIntent.TitleChanged(it)) },
                body = state.body,
                onBodyChange = { onIntent(AddNoteIntent.BodyChanged(it)) },
                onSave = { onIntent(AddNoteIntent.SaveNote) },
                isSaving = state.isSaving
            )

            if (state.isSaving) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun AddNoteForm(
    title: String,
    onTitleChange: (String) -> Unit,
    body: String,
    onBodyChange: (String) -> Unit,
    onSave: () -> Unit,
    isSaving: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = title,
            label = { Text(text = stringResource(R.string.title_label)) },
            onValueChange = onTitleChange,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = !isSaving
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = body,
            label = { Text(text = stringResource(R.string.description_label)) },
            onValueChange = onBodyChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            enabled = !isSaving
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onSave,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            enabled = !isSaving && title.isNotBlank()
        ) {
            Text(
                text = stringResource(R.string.save_label),
                style = MaterialTheme.typography.button.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddNoteForm() {
    MyDiaryTheme {
        AddNoteForm(
            title = "Sample Title",
            onTitleChange = {},
            body = "Sample Body Text",
            onBodyChange = {},
            onSave = {},
            isSaving = false
        )
    }
}
