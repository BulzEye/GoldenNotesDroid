package com.example.goldennotesdroid.network

import com.example.goldennotesdroid.model.ModifyNoteBody
import com.example.goldennotesdroid.model.Note
import com.example.goldennotesdroid.model.NotesResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.Date

private val moshi = Moshi.Builder()
    .add(Date::class.java, Rfc3339DateJsonAdapter())
    .addLast(KotlinJsonAdapterFactory())
    .build()

private val BASE_URL = "https://poised-miniskirt-tuna.cyclic.app"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BackendApiService {
    @Headers("Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY1N2VkN2E2MGIzYjQwYTM2NGM0M2YwOSIsImlhdCI6MTcxMzczMDA5MSwiZXhwIjoxNzE2MzIyMDkxfQ.Jod93Qc-FJJK283BImYXAkLIkR3vT2cktJj5bU62b9Y")
    @GET("getNotes")
    suspend fun getNotes(): NotesResponse

    @Headers("Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY1N2VkN2E2MGIzYjQwYTM2NGM0M2YwOSIsImlhdCI6MTcxMzczMDA5MSwiZXhwIjoxNzE2MzIyMDkxfQ.Jod93Qc-FJJK283BImYXAkLIkR3vT2cktJj5bU62b9Y")
    @POST("modifynote")
    suspend fun modifyNote(@Body reqBody: ModifyNoteBody): Note
}

object GoldenBackend {
    val retrofitService: BackendApiService by lazy {
        retrofit.create(BackendApiService::class.java)
    }
}