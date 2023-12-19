package com.example.goldennotesdroid.adapter

import android.content.Context
import android.service.autofill.Dataset
import android.util.Log
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

class NotesAdapter : ListAdapter<Note, NotesAdapter.NotesViewHolder>(DiffCallback) {
    class NotesViewHolder(
        private var binding: NoteListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
//        val noteContainer: MaterialCardView = view.findViewById(R.id.note_container)
//        val titleTextView: TextView = view.findViewById(R.id.notes_item_title)
//        val bodyTextView: TextView = view.findViewById(R.id.notes_item_body)
        fun bind(curNote: Note) {
            binding.note = curNote
            binding.executePendingBindings()
        }
        fun setClickListener(curNote: Note) {
            binding.noteContainer.setOnClickListener{
//                Log.d("GoldenNotesArguments", curNote.title)
                val action = NotesFragmentDirections.actionNotesFragmentToEditNoteFragment(curNote.title, curNote._id, curNote.body)
                binding.root.findNavController().navigate(action)
//                holder.view.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
//        val adapterLayout = LayoutInflater.from(parent.context)
//            .inflate(R.layout.note_list_item, parent, false)
        return NotesViewHolder(NoteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
//        val dataset = DataSource().loadNotes()
        val item = getItem(position)
        holder.bind(item)
        holder.setClickListener(item)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.title == newItem.title && oldItem.body == newItem.body
        }

    }
}