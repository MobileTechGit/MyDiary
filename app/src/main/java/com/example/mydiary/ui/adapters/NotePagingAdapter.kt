package com.example.mydiary.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiary.R
import com.example.mydiary.db.entity.Note
import com.example.mydiary.utils.MyDateUtils

class NotePagingAdapter : PagingDataAdapter<Note, NotePagingAdapter.NoteViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(parent)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindTo(item)
    }

    class NoteViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
    ) {

        private val titleTv = itemView.findViewById<TextView>(R.id.title_tv)
        private val bodyTv = itemView.findViewById<TextView>(R.id.body_tv)
        private val createdDateTv = itemView.findViewById<TextView>(R.id.created_date_tv)

        fun bindTo(item: Note?) {
            titleTv.text = item?.title
            bodyTv.text = item?.body
            createdDateTv.text = MyDateUtils.getTimeThenDayThenMonth(item?.createdDate)
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.noteId == newItem.noteId
            }
        }
    }
}