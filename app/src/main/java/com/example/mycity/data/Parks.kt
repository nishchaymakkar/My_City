package com.example.mycity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Parks(
    @DrawableRes val image: Int,
    val parkName: String,
    @StringRes val parkDescription: Int
)
