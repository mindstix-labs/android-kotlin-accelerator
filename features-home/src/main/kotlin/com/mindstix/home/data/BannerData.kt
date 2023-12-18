package com.mindstix.home.data

import com.mindstix.features.login.R

data class BannerData(val discount: String, val description: String, val bannerImage: Int)

val bannerList = listOf(
    BannerData("Get 20% off", "On drool-worthy treats from Kfc", R.drawable.f1),
    BannerData("Get 50% off", "On drool-worthy treats from Dominos", R.drawable.f2),
    BannerData("Get 60% off", "On drool-worthy treats from Pizza", R.drawable.f3),
    BannerData("Get 70% off", "On drool-worthy treats from Burger", R.drawable.f5),
    BannerData("Get 30% off", "On drool-worthy treats from Biryani", R.drawable.f6)
)