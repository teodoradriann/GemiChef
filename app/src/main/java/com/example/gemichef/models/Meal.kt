package com.example.gemichef.models

import java.util.Vector

data class Meal (
    var day: String? = null,
    var time: String? = null,
    var mealName: String? = null,
    var ingredients: Vector<String>? = null,
    var calories: Double? = null,
    var protein: Double? = null,
    var carbs: Double? = null,
    var fats: Double? = null
)
