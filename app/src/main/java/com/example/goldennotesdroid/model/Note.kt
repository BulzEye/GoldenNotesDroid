package com.example.goldennotesdroid.model

import androidx.annotation.StringRes
import java.util.Date

data class Note(
//    @StringRes val titleResourceId: Int,
//    @StringRes val bodyResourceId: Int
    val _id: String,
    val user: String,
    val title: String,
    val body: String?,
    val createdAt: Date,
    val updatedAt: Date,
    val __v: Int
) {

}