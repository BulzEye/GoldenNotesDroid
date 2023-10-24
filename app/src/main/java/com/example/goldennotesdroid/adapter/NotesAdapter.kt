package com.example.goldennotesdroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.goldennotesdroid.NotesFragmentDirections
import com.example.goldennotesdroid.R
import com.example.goldennotesdroid.model.Note
import com.google.android.material.card.MaterialCardView

class NotesAdapter(
    private val context: Context,
    private val dataset: List<Note>
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    class NotesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val noteContainer: MaterialCardView = view.findViewById(R.id.note_container)
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
        val titleText = context.resources.getString(item.titleResourceId)
        val bodyText = context.resources.getString(item.bodyResourceId)
        holder.titleTextView.text = titleText
        holder.bodyTextView.text = bodyText
        holder.noteContainer.setOnClickListener{
            val action = NotesFragmentDirections.actionNotesFragmentToEditNoteFragment(titleText, bodyText)
            holder.view.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = dataset.size
}