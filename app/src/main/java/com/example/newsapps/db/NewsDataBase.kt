package com.example.newsapps.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapps.models.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDataBase : RoomDatabase(){
    abstract fun newsDao(): NewsDao
}