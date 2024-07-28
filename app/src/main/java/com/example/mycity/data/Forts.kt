package com.example.mycity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Forts(
    @DrawableRes val image: Int,
    val fortName: String,
    @StringRes val fortDescription: Int
)
