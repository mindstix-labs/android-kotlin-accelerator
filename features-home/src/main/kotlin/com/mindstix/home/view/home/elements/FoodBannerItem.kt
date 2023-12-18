package com.mindstix.home.view.home.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mindstix.home.data.BannerData

/*
* function to create food banner vertically
* bannerData - data for banner card
* */

@Composable
fun FoodBannerItem(bannerData: List<BannerData>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(bannerData) { item ->
            FoodCard(bannerData = item)
        }
    }
}