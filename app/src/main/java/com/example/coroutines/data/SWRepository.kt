package com.example.coroutines.data

class SWRepository(private val api: SWAPI) {
    suspend fun getPeople() = api.getPeople().await().body()
    suspend fun getStarships() = api.getStarships().await().body()
    suspend fun getPlanets() = api.getPlanets().await().body()
}