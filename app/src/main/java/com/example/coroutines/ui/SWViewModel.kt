package com.example.coroutines.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutines.data.SWRepository
import kotlinx.coroutines.*

class SWViewModel(private val repository: SWRepository) : ViewModel() {
    fun getPeople() {
        Log.d("LOG", "show loading")
        //  example showing how to call in parallel the endpoints
        viewModelScope.launch {
            launch {
                val people = repository.getPeople()
                Log.d("LOG", "people: $people")
            }
            delay(3500) // only to test the difference time between the calls
            launch {
                val startships = repository.getStarships()
                Log.d("LOG", "startships: $startships")
            }
            delay(3500) // only to test the difference time between the calls
            launch {
                val planets = repository.getPlanets()
                Log.d("LOG", "planets: $planets")
            }
            Log.d("LOG", "dismiss loading")
        }
    }
}