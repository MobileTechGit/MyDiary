package com.example.mydiary.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydiary.R
import com.example.mydiary.databinding.ActivityMainBinding
import com.example.mydiary.ui.adapters.NoteAdapter
import com.example.mydiary.ui.adapters.NotePagingAdapter
import com.example.mydiary.viewmodels.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val noteViewModel: NotesViewModel by viewModels()
    private lateinit var adapter: NoteAdapter
    private lateinit var pagingAdapter: NotePagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        if (::binding.isInitialized) {
            binding.activity = this
        }

        /*if (!this::adapter.isInitialized) {
            adapter = NoteAdapter(mutableListOf())
        }

        lifecycleScope.launch {
            noteViewModel.getAllNotes().collectLatest {
                Log.i(TAG, "onResume: $it")
                adapter.notesList = it
                binding.noteRv.adapter = adapter
                binding.noteRv.layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }*/

        pagingAdapter = NotePagingAdapter()
        binding.noteRv.adapter = pagingAdapter
        binding.noteRv.layoutManager = LinearLayoutManager(this@MainActivity)
        lifecycleScope.launch {
            noteViewModel.pagingNotesFlow.collectLatest {
                pagingAdapter.submitData(it)
            }
        }

    }

    fun onClickAddNote(v: View) {
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)
    }

}