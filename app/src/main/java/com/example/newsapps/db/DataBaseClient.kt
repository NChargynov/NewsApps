package com.example.newsapps.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

class DataBaseClient(){

    fun provideDatabase(context: Context) : NewsDataBase{
        return Room.databaseBuilder(
            context, NewsDataBase::class.java,
            "news.db")
            .build()
    }

}