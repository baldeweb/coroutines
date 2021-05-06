package com.example.coroutines.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutines.data.SWRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SWViewModel(private val repository: SWRepository) : ViewModel() {
    fun getPeople() {
        Log.d("LOG", "show loading")
        viewModelScope.launch(Dispatchers.IO) {
            val people = repository.getPeople()
            Log.d("LOG", "people: $people")
            Log.d("LOG", "dismiss loading")
        }
    }
}