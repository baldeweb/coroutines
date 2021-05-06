package com.example.coroutines.data

import com.example.coroutines.data.dto.PeopleDTO
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface SWAPI {
    @GET("people/1/")
    fun getPeople(): Deferred<Response<PeopleDTO>>
}