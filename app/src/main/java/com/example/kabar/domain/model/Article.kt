package com.example.kabar.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Article(
    val author: String?,
    val content: String,
    val description: String?,
    val publishedAt: String?,
    val source: Source, //Source is is an object .....
    val title: String?,
    @PrimaryKey val url: String,
    val urlToImage: String?
): Parcelable

/*
1- in databases we can only saves premitive data like string,interger,float
2- so we have to convert Source type to premitive data type
3- we can do it by type convertor ...
 */