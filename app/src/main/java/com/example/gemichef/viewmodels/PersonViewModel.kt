package com.example.gemichef.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gemichef.models.API_KEY
import com.example.gemichef.models.Meal
import com.example.gemichef.models.Person
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.TextPart
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.util.Vector

class PersonViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(Person())

    val uiState: StateFlow<Person> = _uiState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()


    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        systemInstruction = Content(
            parts = listOf(
                TextPart(
                    """
        You are an experienced nutritionist and body builder. I will send you information about a person in this format:
        - age
        - gender
        - weight
        - height
        - fitness objective

        **Your task:**

        - Calculate the person's BMI.
        - Provide an alimentary plan that satisfies their fitness objective based on that person BMI.
        - The plan should cover all 7 days of the week.
        - For each day, provide meals for Breakfast, Lunch, and Dinner.
        - Each meal should include:
            - Name of the meal
            - Ingredients
            - Calories
            - Protein
            - Carbs
            - Fats

        **Output Format:**

        - **You must provide the output in JSON format**, following this structure:
        
        {
          "mealPlan": {
            "Monday": {
              "Breakfast": {
                "mealName": "...",
                "ingredients": ["..."],
                "calories": ...,
                "protein": ...,
                "carbs": ...,
                "fats": ...
              },
              "Lunch": { ... },
              "Dinner": { ... }
            },
            "Tuesday": { ... },
            "...": { ... },
            "Sunday": { ... }
          }
        }

        - Use metric units (liters, grams, etc...)
        - Do not omit any days or meals.
        - Do not return null values.

        **Note:**

        - Specify days by their names (e.g., Monday, Tuesday).
        - Ensure all nutritional information is accurate.
    """.trimIndent(),
                )
            )
        ),
        generationConfig = generationConfig {
            responseMimeType = "application/json"
        },
        apiKey = API_KEY
    )

    private val chat = generativeModel.startChat()

    private suspend fun onMealPlanRequest(personData: String): Boolean {
        return try {
            val response = withContext(Dispatchers.IO) { chat.sendMessage(personData).text }
            if (!response.isNullOrEmpty()) {
                try {
                    val jsonResponse = JSONObject(response)
                    buildMealPlan(jsonResponse)
                    showMealPlan()
                    true
                } catch (e: Exception) {
                    Log.d("Error", "Parsing error: ${e.message}")
                    false
                }
            } else {
                false
            }
        } catch (e: Exception) {
            Log.d("Error", "Request failed: ${e.message}")
            false
        }
    }

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

    fun updateSelectedDay(selectedDay: String) {
        if (selectedDay == "Home") {
            _uiState.value = _uiState.value.copy(selectedDay = null)
            return
        }
        _uiState.value = _uiState.value.copy(selectedDay = selectedDay)
    }

    private fun buildMealPlan(jsonObject: JSONObject) {
        val mealPlan = jsonObject.getJSONObject("mealPlan")
        val days = mealPlan.keys()
        for (day in days) {
            val times = mealPlan.getJSONObject(day).keys()
            for (time in times) {
                val meal = Meal()
                meal.day = day.toString()
                meal.time = time.toString()
                meal.mealName = mealPlan.getJSONObject(day).getJSONObject(time).getString("mealName")
                meal.ingredients = mealPlan.getJSONObject(day).getJSONObject(time).getJSONArray("ingredients").let {
                    val ingredients = Vector<String>()
                    for (i in 0 until it.length()) {
                        ingredients.add(it.getString(i))
                    }
                    ingredients
                }
                meal.calories = mealPlan.getJSONObject(day).getJSONObject(time).getDouble("calories")
                meal.protein = mealPlan.getJSONObject(day).getJSONObject(time).getDouble("protein")
                meal.carbs = mealPlan.getJSONObject(day).getJSONObject(time).getDouble("carbs")
                meal.fats = mealPlan.getJSONObject(day).getJSONObject(time).getDouble("fats")
                addMeal(meal)
            }
        }
    }

    private fun addMeal(meal: Meal) {
        _uiState.value.lunchPlan.add(meal)
    }

    private fun showMealPlan() {
        Log.d("Meal Plan", _uiState.value.lunchPlan.toString())
    }

    fun sendToGemini(onResult: (Boolean) -> Unit) {
        if (_uiState.value.gender != null && _uiState.value.age != null &&
            _uiState.value.weight != null && _uiState.value.height != null &&
            _uiState.value.fitnessObjective != null) {

            if (_uiState.value.lunchPlan != emptyList<Meal>()) {
                _uiState.value.lunchPlan.clear()
            }

            val personData = "Age: ${_uiState.value.age.toString()}, Gender: ${_uiState.value.gender.toString()}, " +
                    "Weight: ${_uiState.value.weight.toString()}, Height: ${_uiState.value.height.toString()}, " +
                    "Fitness Objective ${_uiState.value.fitnessObjective.toString()}"

            viewModelScope.launch {
                _isLoading.value = true
                val success = onMealPlanRequest(personData)
                _isLoading.value = false
                onResult(success)
            }
        } else {
            onResult(false)
        }
    }
}