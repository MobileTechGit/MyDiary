package com.example.mydiary.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiary.R
import com.example.mydiary.db.entity.Note

class NoteAdapter(list: List<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    var notesList: List<Note>
    init {
        notesList =list
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTv: TextView
        val bodyTv: TextView
        init {
            titleTv = itemView.findViewById(R.id.title_tv)
            bodyTv = itemView.findViewById(R.id.body_tv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTv.text = notesList.get(position).title
        holder.bodyTv.text = notesList.get(position).body
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

}