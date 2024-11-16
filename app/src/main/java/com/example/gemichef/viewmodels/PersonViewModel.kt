package com.example.gemichef.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.gemichef.models.Person
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PersonViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(Person())

    val uiState: StateFlow<Person> = _uiState.asStateFlow()

    fun updateGender(gender: String) {
        _uiState.value = _uiState.value.copy(gender = gender)
    }

    fun updateAge(age: Int) {
        _uiState.value = _uiState.value.copy(age = age)
    }

    fun updateWeight(weight: Int) {
        _uiState.value = _uiState.value.copy(weight = weight)
    }

    fun updateHeight(height: Int) {
        _uiState.value = _uiState.value.copy(height = height)
    }

    fun updateFitnessObjective(fitnessObjective: String) {
        _uiState.value = _uiState.value.copy(fitnessObjective = fitnessObjective)
    }

    fun sendToGemini() : Boolean {
        if (_uiState.value.gender != null && _uiState.value.age != null &&
            _uiState.value.weight != null && _uiState.value.height != null &&
            _uiState.value.fitnessObjective != null) {
            // log.d pentru toate fields
            Log.d("Gender", _uiState.value.gender.toString())
            Log.d("Age", _uiState.value.age.toString())
            Log.d("Weight", _uiState.value.weight.toString())
            Log.d("Height", _uiState.value.height.toString())
            Log.d("Fitness Objective", _uiState.value.fitnessObjective.toString())
            return true;
        }
        Log.d("Gender", _uiState.value.gender.toString())
        Log.d("Age", _uiState.value.age.toString())
        Log.d("Weight", _uiState.value.weight.toString())
        Log.d("Height", _uiState.value.height.toString())
        Log.d("Fitness Objective", _uiState.value.fitnessObjective.toString())
        return false
    }

}