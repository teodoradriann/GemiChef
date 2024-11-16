package com.example.gemichef.models

import java.util.Vector

data class Meal (
    val name: String? = null,
    val ingredients: Vector<String>? = null,
    val calories: Double? = null,
    val protein: Double? = null,
    val carbs: Double? = null,
    val fat: Double? = null
)
