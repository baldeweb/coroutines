package com.example.coroutines.data

class SWRepository(private val api: SWAPI) {
    suspend fun getPeople() = api.getPeople().await().body()
}