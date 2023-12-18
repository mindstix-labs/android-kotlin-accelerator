package com.mindstix.home.data

import com.mindstix.features.login.R


data class RestaurantData(
    val discount: String,
    val title: String,
    val rating: String,
    val type: String,
    val location: String,
    val resImage: Int
)

val restaurantData = listOf(
    RestaurantData("Get 20% off", "Dominos", "3.9(200+)", "Pizzas", "Baner", R.drawable.f1),
    RestaurantData("Get 50% off", "Kfc", "3.8(500+)", "Burger", "Hinjewadi", R.drawable.f2),
    RestaurantData("Get 60% off", "Burger King", "4.8(200+)", "Burger", "Aundh", R.drawable.f3),
    RestaurantData(
        "Get 70% off",
        "Shawarma King",
        "2.8(700+)",
        "Shawarma",
        "Koregaon Park",
        R.drawable.f5
    ),
    RestaurantData("Get 30% off", "Biryani Badshah", "1.8(900+)", "Biryani", "BAner", R.drawable.f6)
)