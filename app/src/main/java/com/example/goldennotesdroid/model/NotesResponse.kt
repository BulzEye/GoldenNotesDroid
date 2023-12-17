package com.example.goldennotesdroid.model

data class NotesResponse(
    val user: String,
    val notes: List<Note>
)