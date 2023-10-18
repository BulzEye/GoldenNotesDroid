package com.example.goldennotesdroid.data

import com.example.goldennotesdroid.R
import com.example.goldennotesdroid.model.Note

class DataSource {
    fun loadNotes(): List<Note> {
        return listOf<Note>(
            Note(R.string.sample_title_1, R.string.sample_note_1),
            Note(R.string.sample_title_2, R.string.sample_note_2)
        )
    }
}