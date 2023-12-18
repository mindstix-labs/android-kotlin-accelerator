package com.mindstix.home.view.home.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.mindstix.home.data.BannerData


/*
* function to create food card
* bannerData - data for banner card
* */

@OptIn(ExperimentalCoilApi::class)
@Composable
fun FoodCard(bannerData: BannerData) {
    val painter = rememberImagePainter(
        data = bannerData.bannerImage,
    )
    Card(
        modifier = Modifier
            .width(400.dp)
            .height(200.dp)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Box(
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = bannerData.discount,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                    fontSize = 32.sp
                )
                Text(
                    text = bannerData.description,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}