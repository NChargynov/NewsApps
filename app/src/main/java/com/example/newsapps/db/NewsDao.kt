package com.example.newsapps.db

import androidx.room.*
import com.example.newsapps.models.Article

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(dto: Article)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllArticles(dto: List<Article>)

    @Query("SELECT * FROM article")
    suspend fun getAllArticles(): MutableList<Article>

    @Query("SELECT * FROM article WHERE isFavorite == 1")
    suspend fun getFavoriteArticles(): MutableList<Article>

    @Delete()
    suspend fun deleteFavoriteArticle(dto: Article)

    @Query("DELETE FROM article")
    suspend fun deleteAllArticles()


}