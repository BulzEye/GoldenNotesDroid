package com.example.goldennotesdroid

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goldennotesdroid.model.ModifyNoteBody
import com.example.goldennotesdroid.model.Note
import com.example.goldennotesdroid.model.NoteInfo
import com.example.goldennotesdroid.network.GoldenBackend
import kotlinx.coroutines.launch
import kotlin.reflect.typeOf

enum class EditNoteApiStatus { NONE, ERROR, SUCCESS }

class EditNoteViewModel : ViewModel() {
    private var __id = MutableLiveData<String?>()
    val _id: LiveData<String?> = __id

    private var _title = MutableLiveData<String>()
    val title:  LiveData<String> = _title

    private var _body = MutableLiveData<String>()
    val body: LiveData<String> = _body

    private var _submitStatus = MutableLiveData<EditNoteApiStatus>()
    val submitStatus: LiveData<EditNoteApiStatus> = _submitStatus

    fun getArgumentsAndSetData(arguments: Bundle) {
        Log.d("GNViewModel", arguments.getString("__id")?:"isnull")
        __id.value = arguments.getString("_id").toString()
        _title.value = arguments.getString("noteTitle").toString()
        _body.value = arguments.getString("noteBody").toString()
    }

    fun updateTitle(newTitle: Editable) {
        if(title.value != newTitle.toString()) {
            _title.value = newTitle.toString()
        }
    }

    fun updateBody(newBody: Editable) {
        if(body.value != newBody.toString()) {
            _body.value = newBody.toString()
        }
    }

    fun submitData(context: Context) {
        Log.d("NotesSubmit", __id.value.toString() + (__id.value?:"isnull"))
        if (__id.value == null || __id.value == "null") {
            Log.i("GoldenNotes", "Note ID is null")
            // null ID means we are creating a new note, not updating an existing one
            viewModelScope.launch {
                val reqBody = NoteInfo(title.value?:"", body.value?:"")
                try {
                    val response = GoldenBackend.retrofitService.addNote(reqBody)
                    _submitStatus.value = EditNoteApiStatus.SUCCESS
                    val toast = Toast.makeText(context, "New note added!", Toast.LENGTH_LONG)
                    toast.show()
                    return@launch
                }
                catch (e: Exception) {
                    _submitStatus.value = EditNoteApiStatus.ERROR
                    Log.e("GoldenNotes", "Could not update note: $e")
                }
            }
            return
        }
        else if (title.value != "" || body.value != "") {
            viewModelScope.launch {
                val reqBody = ModifyNoteBody(__id.value as String, NoteInfo(title.value?:"", body.value?:""))
                try {
                    val response = GoldenBackend.retrofitService.modifyNote(reqBody)
                    _submitStatus.value = EditNoteApiStatus.SUCCESS
                    val toast = Toast.makeText(context, "Note successfully updated", Toast.LENGTH_LONG)
                    toast.show()
                    return@launch
                }
                catch (e: Exception) {
                    _submitStatus.value = EditNoteApiStatus.ERROR
                    Log.e("GoldenNotes", "Could not update note: $e")
                }
            }
        }
    }
}