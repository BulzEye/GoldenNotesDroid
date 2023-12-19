package com.example.goldennotesdroid

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditNoteViewModel : ViewModel() {
    private val __id = MutableLiveData<String>()
    val _id: LiveData<String> = __id

    private val _title = MutableLiveData<String>()
    val title:  LiveData<String> = _title

    private val _body = MutableLiveData<String>()
    val body: LiveData<String> = _body

    fun getArgumentsAndSetData(arguments: Bundle) {
//        Log.d("GNViewModel", arguments.getString("noteBody").toString())
        __id.value = arguments.getString("_id").toString()
        _title.value = arguments.getString("noteTitle").toString()
        _body.value = arguments.getString("noteBody").toString()
    }

}