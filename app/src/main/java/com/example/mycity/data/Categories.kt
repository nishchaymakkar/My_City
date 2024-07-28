package com.example.mycity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Categories(
    val id: Int,
    val category: Boolean,
    @DrawableRes val image: Int,
     val categoryName: String
)
