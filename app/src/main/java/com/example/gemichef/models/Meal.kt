package com.example.gemichef.models

import java.util.Vector

data class Meal (
    var mealName: String? = null,
    var ingredients: Vector<String>? = null,
    var calories: Double? = null,
    var protein: Double? = null,
    var carbs: Double? = null,
    var fats: Double? = null
)
