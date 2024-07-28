package com.example.mycity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Monuments(
    @DrawableRes val image: Int,
    val monumentName: String,
    @StringRes val monumentDescription:Int
)
