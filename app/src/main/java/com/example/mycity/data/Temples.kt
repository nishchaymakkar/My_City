package com.example.mycity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Temples(
    @DrawableRes val image: Int,
    val templeName: String,
    @StringRes val templeDescription: Int
)
