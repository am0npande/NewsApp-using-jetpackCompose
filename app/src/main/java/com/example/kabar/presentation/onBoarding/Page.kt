package com.example.kabar.presentation.onBoarding

import android.media.MediaDescription
import androidx.annotation.DrawableRes
import com.example.kabar.R

data class Page(
    val title:String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the prinitng and typeSetting industry",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the prinitng and typeSetting industry",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the prinitng and typeSetting industry",
        image = R.drawable.onboarding3
    )

)
