package com.example.goldennotesdroid.network

import com.example.goldennotesdroid.model.Note
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val BASE_URL = "https://poised-miniskirt-tuna.cyclic.app"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BackendApiService {
    @Headers("Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY1N2VkN2E2MGIzYjQwYTM2NGM0M2YwOSIsImlhdCI6MTcwMjgxMTU1OCwiZXhwIjoxNzA1NDAzNTU4fQ.4Xdq9ycl4G0DxmkxYj1Pmf1MccduAOAqzrFU-CbliIg")
    @GET("getNotes")
    suspend fun getNotes(): List<Note>
}

object GoldenBackend {
    val retrofitService: BackendApiService by lazy {
        retrofit.create(BackendApiService::class.java)
    }
}