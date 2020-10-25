package com.example.newsapps.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapps.models.Article

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(dto: Article)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllArticle(dto: List<Article>)

    @Query("SELECT * FROM article")
    suspend fun getAllArticles(): MutableList<Article>

    @Query("SELECT * FROM article WHERE isFavorite == 1")
    suspend fun getFavoriteArticles(): MutableList<Article>

}