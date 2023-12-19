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

class EditNoteViewModel : ViewModel() {
    private var __id = MutableLiveData<String>()
    val _id: LiveData<String> = __id

    private var _title = MutableLiveData<String>()
    val title:  LiveData<String> = _title

    private var _body = MutableLiveData<String>()
    val body: LiveData<String> = _body

    fun getArgumentsAndSetData(arguments: Bundle) {
//        Log.d("GNViewModel", arguments.getString("noteBody").toString())
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
        __id.value?.let {
            viewModelScope.launch {
                val reqBody = ModifyNoteBody(it, NoteInfo(title.value?:"", body.value?:""))
                try {
                    val response = GoldenBackend.retrofitService.modifyNote(reqBody)
                    val toast = Toast.makeText(context, "Note successfully updated", Toast.LENGTH_LONG)
                    toast.show()
                }
                catch (e: Exception) {
                    Log.e("GoldenNotes", "Could not update note: $e")
                }
            }
        }
    }
}