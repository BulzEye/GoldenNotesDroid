package com.example.goldennotesdroid.adapter

import android.content.Context
import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goldennotesdroid.NotesFragmentDirections
import com.example.goldennotesdroid.R
import com.example.goldennotesdroid.data.DataSource
import com.example.goldennotesdroid.databinding.NoteListItemBinding
import com.example.goldennotesdroid.model.Note
import com.google.android.material.card.MaterialCardView

class NotesAdapter(
    private var binding: NoteListItemBinding
) : ListAdapter<Note, NotesAdapter.NotesViewHolder>(DiffCallback) {
    class NotesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//        val noteContainer: MaterialCardView = view.findViewById(R.id.note_container)
//        val titleTextView: TextView = view.findViewById(R.id.notes_item_title)
//        val bodyTextView: TextView = view.findViewById(R.id.notes_item_body)
        fun bind(curNote: Note) {
            binding.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_list_item, parent, false)
        return NotesViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val dataset = DataSource().loadNotes()
        val item = dataset[position]
        val titleText = context.resources.getString(item.titleResourceId)
        val bodyText = context.resources.getString(item.bodyResourceId)
        holder.titleTextView.text = titleText
        holder.bodyTextView.text = bodyText
        holder.noteContainer.setOnClickListener{
            val action = NotesFragmentDirections.actionNotesFragmentToEditNoteFragment(titleText, bodyText)
            holder.view.findNavController().navigate(action)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            TODO("Not yet implemented")
        }

    }
}