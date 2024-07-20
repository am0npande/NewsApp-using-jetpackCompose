package com.example.kabar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.kabar.domain.model.Article

@Database(entities = [Article::class], version = 2)
@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDataBase: RoomDatabase() {

    abstract val newsDao:NewsDao
}

//it is abstract because room will implement it for us