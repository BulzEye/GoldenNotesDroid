package com.example.goldennotesdroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.goldennotesdroid.R
import com.example.goldennotesdroid.model.Note

class NotesAdapter(
    private val context: Context,
    private val dataset: List<Note>
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    class NotesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.notes_item_title)
        val bodyTextView: TextView = view.findViewById(R.id.notes_item_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_list_item, parent, false)
        return NotesViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val item = dataset[position]
        holder.titleTextView.text = context.resources.getString(item.titleResourceId)
        holder.bodyTextView.text = context.resources.getString(item.bodyResourceId)
    }

    override fun getItemCount() = dataset.size
}