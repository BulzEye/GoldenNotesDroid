package com.example.goldennotesdroid.model

data class NotesResponse(
    val user: User,
    val notes: List<Note>
)