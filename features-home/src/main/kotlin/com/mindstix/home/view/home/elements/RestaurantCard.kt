package com.mindstix.home.view.home.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.mindstix.home.data.RestaurantData

/*
* function to create restaurant card
* restaurantData - data for restaurant card
* description - description of card
* foodDeliveryText - food delivery text
* */

@OptIn(ExperimentalCoilApi::class)
@Composable
fun RestaurantCard(
    restaurantData: RestaurantData,
    description: String,
    foodDeliveryText: String,
) {
    val painter = rememberImagePainter(
        data = restaurantData.resImage,
    )
    Row {
        Box(
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .padding(16.dp)
                .shadow(14.dp),
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {
            Text(
                text = restaurantData.title,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = restaurantData.rating,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Black,
            )
            Text(
                text = restaurantData.type,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray,
            )
            Text(
                text = restaurantData.location,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray,
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Text(
                    text = foodDeliveryText,
                    modifier = Modifier.padding(top = 30.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Black,
                )
            }
        }
    }
}
