/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.home.view.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mindstix.features.login.R
import com.mindstix.home.data.bannerList
import com.mindstix.home.data.restaurantData
import com.mindstix.home.view.home.elements.FoodBannerItem
import com.mindstix.home.view.home.elements.RestaurantCard
import com.mindstix.home.view.home.elements.TotalRestaurantText

/**
 * Composable function representing the Home Screen.
 *
 * @author Anmol Kashyap
 */
@Composable
fun HomeScreen() {
    val bannerData = bannerList
    val restaurantData = restaurantData
    val scrollState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = scrollState,
    ) {
        item {
            FoodBannerItem(bannerData)
        }
        item {
            TotalRestaurantText(stringResource(id = R.string.total_restaurant_text))
        }
        items(restaurantData) { item ->
            RestaurantCard(
                item,
                stringResource(id = R.string.upto),
                stringResource(id = R.string.free_delivery),
            )
        }
    }
}
