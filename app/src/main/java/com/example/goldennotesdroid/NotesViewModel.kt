package com.example.goldennotesdroid

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goldennotesdroid.model.Note
import com.example.goldennotesdroid.network.GoldenBackend
import kotlinx.coroutines.launch

class NotesViewModel : ViewModel() {
    private val _notesList = MutableLiveData<List<Note>>()
    val notesList: LiveData<List<Note>> = _notesList

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch {
            try {
                _notesList.value = GoldenBackend.retrofitService.getNotes()
                Log.d("GoldenNotes", "Notes: ${_notesList.value}")
            }
            catch (e: Exception) {
                Log.e("GoldenNotes", "Error: Could not fetch notes. Error: ${e.message}")
                _notesList.value = listOf()
            }
        }
    }
}