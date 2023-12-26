package com.example.mvvmwithdaggerandapi.api

import com.example.mvvmwithdaggerandapi.model.People
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getPeople(): Response<List<People>>
}