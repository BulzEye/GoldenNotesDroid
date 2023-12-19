package com.example.goldennotesdroid.model

import java.util.Date

data class User(
    val _id: String,
    val email: String,
    val username: String,
    val password: String,
    val createdAt: Date,
    val updatedAt: Date,
    val __v: Int
)