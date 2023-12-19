package com.example.goldennotesdroid

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goldennotesdroid.adapter.NotesAdapter
import com.example.goldennotesdroid.model.Note

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Note>?) {
    val adapter = recyclerView.adapter as NotesAdapter
//    Log.d("GoldenNotes", "Data: $data")
    adapter.submitList(data)
}