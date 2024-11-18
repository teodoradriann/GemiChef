package com.example.gemichef.views.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gemichef.models.Meal
import java.util.Vector

@Composable
fun MealCard(
    meal: Meal,
    modifier: Modifier = Modifier
) {

    val ingredients = StringBuilder()
    if (meal.ingredients != null) {
        for (ingredient in meal.ingredients!!) {
            ingredients.append(ingredient)
            ingredients.append("\n")
        }
    }

    Box(
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier.padding(24.dp)
            ) {
                Card(
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = meal.time.toString(),
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.W300
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = meal.mealName.toString(),
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W500
                            )
                        )
                        Text(
                            ingredients.toString(),
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W300
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text("Nutritional values: \nCalories: ${meal.calories.toString()} kcal\n" +
                                "Protein: ${meal.protein} g\n" +
                                "Carbohydrates: ${meal.carbs} g\n" +
                                "Fats: ${meal.fats} g",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W200
                            ))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MealCard(){
    MealCard(meal = Meal(
            day = "Monday",
    time = "Breakfast",
    mealName = "Oatmeal with Fruits",
    ingredients = Vector<String>().apply {
        add("50g oatmeal")
        add("200ml almond milk")
        add("1 banana")
        add("1 handful of blueberries")
        add("1 tsp honey")
    },
    calories = 350.0,
    protein = 8.0,
    carbs = 60.0,
    fats = 5.0
    ))
}