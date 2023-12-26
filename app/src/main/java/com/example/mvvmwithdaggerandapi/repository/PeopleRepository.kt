package com.example.mvvmwithdaggerandapi.repository

import com.example.mvvmwithdaggerandapi.api.ApiService
import javax.inject.Inject

class PeopleRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getPeoples() = apiService.getPeople()
}