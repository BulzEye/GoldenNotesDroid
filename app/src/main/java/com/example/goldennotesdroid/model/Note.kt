package com.example.goldennotesdroid.model

import androidx.annotation.StringRes

data class Note(
    @StringRes val titleResourceId: Int,
    @StringRes val bodyResourceId: Int
) {

}