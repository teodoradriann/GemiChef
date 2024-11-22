package com.example.gemichef.views.ui.elements

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gemichef.R
import com.example.gemichef.models.Meal
import java.util.Vector

@Composable
fun MealCard(
    meal: Meal,
    favourites: List<Meal>,
    favouritesAdd: (Meal) -> Unit,
    favouritesDelete: (Meal) -> Unit,
    modifier: Modifier = Modifier
) {

    val ingredients = StringBuilder()
    if (meal.ingredients != null) {
        for (ingredient in meal.ingredients!!) {
            ingredients.append(ingredient)
            ingredients.append("\n")
        }
    }

    var isFavourite by rememberSaveable { mutableStateOf(favourites.contains(meal)) }
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = meal.time.toString(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W300
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = meal.mealName.toString(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W500
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = ingredients.toString(),
                    style = TextStyle(
                        color = Color.DarkGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W300
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Nutritional values:\n" +
                            "Calories: ${meal.calories} kcal\n" +
                            "Protein: ${meal.protein} g\n" +
                            "Carbohydrates: ${meal.carbs} g\n" +
                            "Fats: ${meal.fats} g",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W200
                    )
                )
            }

            IconButton(
                onClick = {
                    if (isFavourite) {
                        favouritesDelete(meal)
                        isFavourite = false
                    } else {
                        favouritesAdd(meal)
                        isFavourite = true
                    }
                },
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = if (favourites.contains(meal)) stringResource(R.string.remv_fav) else stringResource(R.string.add_fav),
                    tint = if (isFavourite) Color.Yellow else Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealCardPreview() {
    MealCard(
        meal = Meal(
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
        ),
        favourites = emptyList(),
        favouritesDelete = {},
        favouritesAdd = {},
    )
}