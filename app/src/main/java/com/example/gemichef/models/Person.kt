package com.example.gemichef.models

data class Person (
    val age: Int? = null,
    val gender: String? = null,
    val weight: Int? = null,
    val height: Int? = null,
    val fitnessObjective: String? = null,
    val lunchPlan: MutableList<Meal>? = null,
    val selectedDay: String? = null
)


val heights = List(120) { (it + 121) }
val weights = List(120) { (it + 51) }
val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")